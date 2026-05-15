# Groovy Toolchain

- State: deterministic-first tranche C
- Toolchain source: local Groovy plus Python validation; Gradle is a convenience task runner.

## Required local commands
```bash
groovy --version
gradle --version
python3 --version
```

## Native validation
```bash
python3 scripts/validate_scaffold.py
gradle validate
```

## Docker validation
```bash
docker build -t groovy-stakeholder .
docker run --rm groovy-stakeholder --list-values
docker run --rm groovy-stakeholder --focus-family metrics --output-format json --seed 42
```

## Notes
- The CLI is dependency-free Groovy and uses only `groovy.json.JsonOutput` plus JDK classes.
- JSON output is one normalized event object per line.
