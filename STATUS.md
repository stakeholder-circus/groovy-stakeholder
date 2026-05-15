# groovy-stakeholder Status

- Role: Groovy deterministic-first parity target
- Parity class: full-parity, deterministic tranche C
- State: tranche-c-implemented-local-only
- Rewrite completeness: 45%
- Functionality completeness: 38%
- Branch: `main`
- Origin: `git@github.com:stakeholder-circus/groovy-stakeholder.git`
- Upstream reference: `https://github.com/giacomo-b/rust-stakeholder`

## Complete in tranche C
- Groovy CLI replaces the Rust scaffold.
- `--list-values`, `--focus-family`, `--output-format text|json`, `--seed`, and `--experimental-provider` are implemented.
- Classic-six and modern-core families have dedicated deterministic renderers.
- Later families produce explicit grouped fallback JSON events.
- Same-seed JSON output is covered by native validation.

## Remaining blockers
- Live provider/runtime support is intentionally fail-fast.
- Later family dedicated parity is deferred.
- Publication remains blocked until governance and remote access are available.

## Next
- Add dedicated later-family implementations in the next tranche.
- Add provider adapters only behind deterministic guardrails and documented fixtures.
