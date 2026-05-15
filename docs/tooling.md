# Groovy Tooling

## Commands
```bash
python3 scripts/validate_scaffold.py
gradle validate
groovy src/main/groovy/stakeholder/StakeholderCli.groovy --list-values
docker build -t groovy-stakeholder .
docker run --rm groovy-stakeholder --focus-family metrics --output-format json --seed 42
```

## Notes
- No IDE-only workflow is required.
- Gradle is intentionally a thin task runner so native validation remains local and deterministic.
