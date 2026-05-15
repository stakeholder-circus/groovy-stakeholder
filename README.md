> [!WARNING]
> This repository is AI-assisted and manually reviewed. It is a local-first Groovy rewrite target in the stakeholder parity program.

# groovy-stakeholder

Deterministic-first Groovy CLI for stakeholder activity generation.

## Status
- Tranche C implementation replaces the imported Rust scaffold with a Groovy CLI.
- No upstream tracking or publication is configured here.
- MIT license notice is preserved in `LICENSE` and source headers.

## CLI
```bash
groovy src/main/groovy/stakeholder/StakeholderCli.groovy --list-values
groovy src/main/groovy/stakeholder/StakeholderCli.groovy --focus-family metrics --output-format json --seed 42
groovy src/main/groovy/stakeholder/StakeholderCli.groovy --experimental-provider
```

Supported required flags:
- `--list-values`
- `--focus-family <family>`
- `--output-format text|json`
- `--seed <integer>`
- `--experimental-provider` fail-fast

## Parity scope
- Dedicated classic-six families: `code-analyzer`, `data-processing`, `jargon`, `metrics`, `network-activity`, `system-monitoring`.
- Dedicated modern-core families: agent, inference, platform, security, observability, delivery, evaluation, retrieval, edge, identity, AIBOM, boundary, embedded, governance, and FinOps families.
- Later families use explicit grouped deterministic fallback events until dedicated parity rows are implemented.

## Validation
```bash
python3 scripts/validate_scaffold.py
gradle validate
docker build -t groovy-stakeholder .
docker run --rm groovy-stakeholder --focus-family metrics --output-format json --seed 42
```

## Documentation
- [STATUS.md](STATUS.md)
- [PARITY.md](PARITY.md)
- [GAPS.md](GAPS.md)
- [docs/toolchain.md](docs/toolchain.md)
- [docs/traceability/first-push-families.md](docs/traceability/first-push-families.md)
