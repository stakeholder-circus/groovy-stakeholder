# Groovy Parity

- Role: local-first full-parity target
- Parity class: deterministic-first tranche C

## Review model
- Rust remains the upstream behavioral source-of-truth.
- `stakeholder-core` remains the shared contract and fixture authority.
- This repo emits deterministic normalized JSON-lines for snapshot comparison.

## Implemented
- Dedicated classic-six family events.
- Dedicated modern-core family events.
- Grouped fallback for later families.
- Same-seed stable JSON output.
- Experimental provider fail-fast.

## Promotion prerequisites
- Native validation green.
- Docker validation green.
- Traceability rows kept current for any behavior promoted beyond fallback.
