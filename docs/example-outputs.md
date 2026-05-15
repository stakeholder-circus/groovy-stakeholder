# Groovy Example Outputs

## List values
```bash
groovy src/main/groovy/stakeholder/StakeholderCli.groovy --list-values
```

## Dedicated family JSON
```bash
groovy src/main/groovy/stakeholder/StakeholderCli.groovy --focus-family metrics --output-format json --seed 42
```

## Later-family grouped fallback
```bash
groovy src/main/groovy/stakeholder/StakeholderCli.groovy --focus-family mcp-a2a-ops --output-format json --seed 7
```

## Experimental provider fail-fast
```bash
groovy src/main/groovy/stakeholder/StakeholderCli.groovy --experimental-provider
```
