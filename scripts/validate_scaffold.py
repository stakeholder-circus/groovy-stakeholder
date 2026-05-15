#!/usr/bin/env python3
import shutil
import subprocess
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
REQUIRED = [
    "README.md",
    "AI_DISCLOSURE.md",
    "PARITY.md",
    "GAPS.md",
    "AGENTS.md",
    "LICENSE",
    "src/main/groovy/stakeholder/StakeholderCli.groovy",
    "tests/test_cli.py",
    "docs/remotes.md",
    "docs/provenance.md",
    "docs/toolchain.md",
    "docs/traceability/first-push-families.md",
    ".githooks/commit-msg",
    ".githooks/pre-push",
    ".github/CODEOWNERS",
    ".github/PULL_REQUEST_TEMPLATE.md",
    ".github/dependabot.yml",
    ".github/workflows/actionlint.yml",
    ".github/workflows/dependency-review.yml",
    ".github/workflows/ci.yml",
    ".github/workflows/ci-native.yml",
    ".github/workflows/docker-smoke.yml",
    "flake.nix",
    "Dockerfile",
    "flake.lock",
    "settings.gradle",
    "build.gradle",
]


def run(command):
    print("$ " + " ".join(command))
    subprocess.run(command, cwd=ROOT, check=True)


def main():
    missing = [p for p in REQUIRED if not (ROOT / p).exists()]
    if missing:
        raise SystemExit("missing Groovy tranche C files: " + ", ".join(missing))
    if shutil.which("groovy") is None:
        raise SystemExit("groovy executable is required for native validation")
    run(["python3", "-m", "unittest", "discover", "-s", "tests"])
    run(["groovy", "src/main/groovy/stakeholder/StakeholderCli.groovy", "--list-values"])
    run(["groovy", "src/main/groovy/stakeholder/StakeholderCli.groovy", "--focus-family", "metrics", "--output-format", "json", "--seed", "42"])
    print("groovy tranche C validation passed")


if __name__ == "__main__":
    try:
        main()
    except subprocess.CalledProcessError as exc:
        sys.exit(exc.returncode)
