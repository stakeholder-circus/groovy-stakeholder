  # Groovy Toolchain

  - State: scaffold-only next-20 prep
  - Toolchain source: `built-in`

  ## Planned commands after promotion
    - `groovy --version`
- `gradle --version`

  ## Scaffold-time checks
  - `python3 scripts/validate_scaffold.py`
  - `/nix/var/nix/profiles/default/bin/nix --extra-experimental-features 'nix-command flakes' flake lock`

  ## Current limitation
  - Groovy and Gradle are already available.
