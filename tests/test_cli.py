import json
import subprocess
import unittest
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
CLI = ROOT / "src/main/groovy/stakeholder/StakeholderCli.groovy"


def run_cli(*args, check=True):
    result = subprocess.run(
        ["groovy", str(CLI), *args],
        cwd=ROOT,
        text=True,
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
    )
    if check and result.returncode != 0:
        raise AssertionError(f"command failed: {result.returncode}\nstdout={result.stdout}\nstderr={result.stderr}")
    return result


def json_lines(output):
    return [json.loads(line) for line in output.splitlines() if line.strip()]


class CliTests(unittest.TestCase):
    def test_list_values_exposes_required_flags_and_families(self):
        payload = json.loads(run_cli("--list-values").stdout)
        self.assertEqual(payload["outputFormats"], ["text", "json"])
        self.assertTrue({"list-values", "focus-family", "output-format", "seed", "experimental-provider"}.issubset(payload["flags"]))
        self.assertEqual(len(payload["parityFamilies"]["classicSix"]), 6)
        self.assertIn("agent-workflows", payload["parityFamilies"]["modernCore"])
        self.assertIn("mcp-a2a-ops", payload["parityFamilies"]["laterGroupedFallback"])

    def test_same_seed_json_is_stable(self):
        first = run_cli("--output-format", "json", "--seed", "42").stdout
        second = run_cli("--output-format", "json", "--seed", "42").stdout
        self.assertEqual(first, second)
        events = json_lines(first)
        self.assertEqual(events[0]["eventType"], "generator.dedicated")
        self.assertEqual(events[0]["parityClass"], "classic-six")
        self.assertEqual(events[-1]["parityClass"], "modern-core")

    def test_focus_family_supports_dedicated_and_grouped_fallback(self):
        dedicated = json_lines(run_cli("--focus-family", "metrics", "--output-format", "json", "--seed", "7").stdout)
        fallback = json_lines(run_cli("--focus-family", "mcp-a2a-ops", "--output-format", "json", "--seed", "7").stdout)
        self.assertEqual(len(dedicated), 1)
        self.assertEqual(dedicated[0]["eventType"], "generator.dedicated")
        self.assertEqual(fallback[0]["eventType"], "generator.groupedFallback")
        self.assertEqual(fallback[0]["parityClass"], "later-family-grouped-fallback")

    def test_experimental_provider_fails_fast(self):
        result = run_cli("--experimental-provider", check=False)
        self.assertEqual(result.returncode, 2)
        self.assertIn("intentionally unavailable", result.stderr)


if __name__ == "__main__":
    unittest.main()
