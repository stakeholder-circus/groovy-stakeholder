# Groovy Edge Cases

- Same seed and same arguments must produce byte-identical JSON-lines.
- Unknown `--focus-family` values fail fast with exit status 2.
- `--experimental-provider` always fails fast in tranche C.
- Later families are not silently treated as fully implemented; they emit `generator.groupedFallback` with `later-family-grouped-fallback` parity class.
