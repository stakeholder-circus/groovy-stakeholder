# Groovy Docker

## Build and test
```bash
docker build -t groovy-stakeholder .
docker run --rm groovy-stakeholder --list-values
docker run --rm groovy-stakeholder --focus-family metrics --output-format json --seed 42
```

## Rationale
- The image runs the same dependency-free Groovy CLI as native validation.
- Docker is the reproducible Linux smoke gate before committing or publishing.
