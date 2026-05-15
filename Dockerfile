FROM groovy:4.0-jdk21
LABEL org.opencontainers.image.title="groovy-stakeholder"
LABEL org.opencontainers.image.description="Deterministic-first Groovy stakeholder CLI"
WORKDIR /app
COPY src/main/groovy/stakeholder/StakeholderCli.groovy /app/src/main/groovy/stakeholder/StakeholderCli.groovy
ENTRYPOINT ["groovy", "/app/src/main/groovy/stakeholder/StakeholderCli.groovy"]
CMD ["--list-values"]
