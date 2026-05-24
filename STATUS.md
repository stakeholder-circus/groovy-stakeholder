# groovy-stakeholder Status

- Role: Groovy deterministic-first parity target
- Parity class: full-parity, deterministic tranche C
- State: native-validated local deterministic tranche
- Rewrite completeness: 49%
- Functionality completeness: 42%
- Branch: `main`
- Origin: `git@github.com:stakeholder-circus/groovy-stakeholder.git`
- Upstream reference: `https://github.com/giacomo-b/rust-stakeholder`

## Complete in tranche C
- Groovy CLI replaces the Rust scaffold.
- `--list-values`, `--focus-family`, `--output-format text|json`, `--seed`, and `--experimental-provider` are implemented.
- Classic-six and modern-core families have dedicated deterministic renderers.
- Later families produce explicit grouped fallback JSON events.
- Same-seed JSON output is covered by native validation.

## Evidence
- `python3 scripts/validate_scaffold.py`
- `python3 -m unittest discover -s tests`
- `groovy src/main/groovy/stakeholder/StakeholderCli.groovy --list-values`
- JSON smoke for `metrics`
- same-seed deterministic JSON diff for `platform-engineering`
- explicit `--experimental-provider local-demo` fail-fast smoke

## Remaining blockers
- Live provider/runtime support is intentionally fail-fast.
- Later family dedicated parity is deferred.
- Publication remains blocked until governance and remote access are available.

## Next
- Add dedicated later-family implementations in the next tranche.
- Add provider adapters only behind deterministic guardrails and documented fixtures.
