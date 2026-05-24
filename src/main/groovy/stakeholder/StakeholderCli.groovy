#!/usr/bin/env groovy
/*
 * MIT License
 *
 * Copyright (c) 2025 giacomo-b
 *
 * Groovy deterministic-first rewrite for stakeholder-circus.
 */
package stakeholder

import groovy.json.JsonOutput

final class StakeholderCli {
    static final List<String> CLASSIC_SIX = [
        'code-analyzer',
        'data-processing',
        'jargon',
        'metrics',
        'network-activity',
        'system-monitoring'
    ].asImmutable()

    static final List<String> MODERN_CORE = [
        'agent-workflows',
        'ai-inference-ops',
        'platform-engineering',
        'supply-chain-security',
        'observability-ai-runtime',
        'delivery-preview-ops',
        'evaluation-and-guardrails',
        'knowledge-retrieval',
        'edge-client-runtime',
        'identity-and-trust',
        'aibom-provenance',
        'agent-boundary-security',
        'embedded-agentic-pipeline',
        'data-governance-compliance',
        'finops-capacity'
    ].asImmutable()

    static final List<String> LATER_FAMILIES = [
        'blockchain-protocol-ops',
        'cross-chain-interop',
        'proof-and-sequencer-ops',
        'hybrid-runtime-ops',
        'capacity-cost-controller',
        'batch-execution-tuner',
        'compiler-maintainer',
        'interop-adapter-engineer',
        'preflight-capacity-planner',
        'simulator-performance-engineer',
        'fhir-profile-generator',
        'smart-launch-oauth',
        'bulk-fhir-population-ops',
        'hl7v2-feed-ops',
        'clinical-workflow-events',
        'dicomweb-imaging-ops',
        'openehr-semantic-record-ops',
        'device-telemetry-clinical',
        'emr-vendor-adapter',
        'ocpp-chargepoint-ops',
        'ocpi-roaming-ops',
        'mcp-a2a-ops',
        'streaming-bus-ops',
        'service-mesh-rpc-ops'
    ].asImmutable()

    static final Map<String, Map<String, String>> DEDICATED = [
        'code-analyzer': [title: 'Code analyzer', schema: 'code-review-signal', message: 'audits typed interfaces, dependency edges, and generated patch provenance'],
        'data-processing': [title: 'Data processing', schema: 'data-pipeline-window', message: 'normalizes batch transforms, embedding corpora, and replayable data slices'],
        'jargon': [title: 'Jargon refresh', schema: 'terminology-pass', message: 'keeps modern agent, platform, protocol, and security terminology precise'],
        'metrics': [title: 'Metrics', schema: 'operations-metric', message: 'correlates latency bands, queue depth, cost signals, and SLO burn'],
        'network-activity': [title: 'Network activity', schema: 'network-flow', message: 'profiles RPC, event-stream, registry, and adapter traffic under replay windows'],
        'system-monitoring': [title: 'System monitoring', schema: 'host-runtime-signal', message: 'stitches host telemetry, sandbox failures, and scheduler pressure into one heartbeat'],
        'agent-workflows': [title: 'Agent workflows', schema: 'agent-workflow-envelope', message: 'routes delegated patch runs through review queues, leases, and approval gates'],
        'ai-inference-ops': [title: 'AI inference ops', schema: 'inference-event', message: 'tracks model routing, cache pressure, fallback chains, and eval movement'],
        'platform-engineering': [title: 'Platform engineering', schema: 'platform-control-plane', message: 'maintains golden paths, workload identity, tenant quotas, and template drift'],
        'supply-chain-security': [title: 'Supply-chain security', schema: 'provenance-check', message: 'gates artifact trust, signed provenance, dependency health, and revocation posture'],
        'observability-ai-runtime': [title: 'Observability AI runtime', schema: 'otel-runtime-event', message: 'records spans, token spend, GPU telemetry, and distributed reasoning traces'],
        'delivery-preview-ops': [title: 'Delivery preview ops', schema: 'preview-rollout', message: 'coordinates preview environments, feature flags, canaries, and rollback windows'],
        'evaluation-and-guardrails': [title: 'Evaluation and guardrails', schema: 'eval-result', message: 'runs rubric checks, schema validation, policy gates, and benchmark regressions'],
        'knowledge-retrieval': [title: 'Knowledge retrieval', schema: 'retrieval-run', message: 'refreshes vector indexes, reranker posture, citations, and corpus freshness'],
        'edge-client-runtime': [title: 'Edge client runtime', schema: 'edge-runtime-event', message: 'stabilizes streaming UI, hydration boundaries, offline sync, and edge cold starts'],
        'identity-and-trust': [title: 'Identity and trust', schema: 'trust-context', message: 'validates signer provenance, workload identity, session trust, and delegated authority'],
        'aibom-provenance': [title: 'AIBOM provenance', schema: 'aibom-record', message: 'versions model lineage, prompt assets, adapter state, and cache metadata'],
        'agent-boundary-security': [title: 'Agent boundary security', schema: 'agent-boundary-alert', message: 'blocks unsafe delegation, tool overreach, principal confusion, and poisoned retrieval'],
        'embedded-agentic-pipeline': [title: 'Embedded agentic pipeline', schema: 'embedded-agent-step', message: 'coordinates constrained inference, firmware toolchains, and deterministic control loops'],
        'data-governance-compliance': [title: 'Data governance compliance', schema: 'governance-check', message: 'applies retention, consent, explainability, and regional handling rules'],
        'finops-capacity': [title: 'FinOps capacity', schema: 'capacity-cost-signal', message: 'balances GPU scheduling, token ceilings, runner economics, and storage growth']
    ].asImmutable()

    static void main(String[] argv) {
        Map opts = parseArgs(argv as List<String>)
        if (opts.help) {
            println usage()
            return
        }
        if (opts.experimentalProvider) {
            System.err.println('experimental provider runtime is intentionally unavailable in deterministic Groovy tranche C')
            System.exit(2)
        }
        if (opts.listValues) {
            println JsonOutput.prettyPrint(JsonOutput.toJson(listValues()))
            return
        }
        if (!(opts.outputFormat in ['text', 'json'])) {
            fail("unsupported --output-format '${opts.outputFormat}'")
        }

        List<String> families = selectedFamilies(opts.focusFamily as String)
        Random rng = new Random((opts.seed as Long) ?: 0L)
        List<Map> events = []
        int sequence = 1
        families.each { String family ->
            events << eventFor(sequence++, family, rng, opts.seed as Long)
        }

        if (opts.outputFormat == 'json') {
            events.each { println JsonOutput.toJson(it) }
        } else {
            events.each { Map event -> println "${event.sequence}. [${event.family}] ${event.message}" }
        }
    }

    static Map parseArgs(List<String> args) {
        Map opts = [listValues: false, focusFamily: null, outputFormat: 'text', seed: 0L, experimentalProvider: false, help: false]
        for (int i = 0; i < args.size(); i++) {
            String arg = args[i]
            switch (arg) {
                case '--help': opts.help = true; break
                case '--list-values': opts.listValues = true; break
                case '--experimental-provider':
                    opts.experimentalProvider = true
                    if (i + 1 < args.size() && !args[i + 1].startsWith('--')) {
                        i++
                    }
                    break
                case '--focus-family': opts.focusFamily = requireValue(args, ++i, arg); break
                case '--output-format': opts.outputFormat = requireValue(args, ++i, arg); break
                case '--seed': opts.seed = parseSeed(requireValue(args, ++i, arg)); break
                default: fail("unknown argument '${arg}'")
            }
        }
        return opts
    }

    static String requireValue(List<String> args, int index, String flag) {
        if (index >= args.size()) fail("${flag} requires a value")
        return args[index]
    }

    static Long parseSeed(String value) {
        try {
            return Long.parseLong(value)
        } catch (NumberFormatException ignored) {
            fail("--seed must be a signed 64-bit integer")
        }
        return 0L
    }

    static List<String> selectedFamilies(String focusFamily) {
        if (focusFamily == null) return (CLASSIC_SIX + MODERN_CORE).asImmutable()
        if (!(focusFamily in allFamilies())) fail("unknown --focus-family '${focusFamily}'")
        return [focusFamily]
    }

    static Map eventFor(int sequence, String family, Random rng, Long seed) {
        boolean dedicated = DEDICATED.containsKey(family)
        Map descriptor = dedicated ? DEDICATED[family] : fallbackDescriptor(family)
        int shard = Math.floorMod(rng.nextInt(), 10_000)
        return [
            eventType: dedicated ? 'generator.dedicated' : 'generator.groupedFallback',
            sequence: sequence,
            timestamp: String.format('T+%06dms', sequence * 137),
            family: family,
            parityClass: dedicated ? (family in CLASSIC_SIX ? 'classic-six' : 'modern-core') : 'later-family-grouped-fallback',
            title: descriptor.title,
            message: "${descriptor.message}; deterministic shard ${shard}",
            schemaRef: [name: descriptor.schema, version: dedicated ? '2026-05-groovy-tranche-c' : '2026-05-grouped-fallback'],
            generationProvenance: [sourceRepo: 'rust-stakeholder', targetRepo: 'groovy-stakeholder', baseline: 'tranche-c-deterministic-first', experimental: false, adapterType: dedicated ? 'static-dedicated-groovy' : 'static-grouped-fallback'],
            context: [seed: seed, normalized: true]
        ]
    }

    static Map fallbackDescriptor(String family) {
        return [title: titleize(family), schema: 'later-family-fallback', message: "records ${family} as grouped deterministic fallback pending dedicated parity"]
    }

    static String titleize(String family) {
        family.split('-').collect { it.capitalize() }.join(' ')
    }

    static Map listValues() {
        return [
            outputFormats: ['text', 'json'],
            flags: ['list-values', 'focus-family', 'output-format', 'seed', 'experimental-provider'],
            parityFamilies: [classicSix: CLASSIC_SIX, modernCore: MODERN_CORE, laterGroupedFallback: LATER_FAMILIES],
            generatorFamilies: allFamilies()
        ]
    }

    static List<String> allFamilies() {
        return (CLASSIC_SIX + MODERN_CORE + LATER_FAMILIES).asImmutable()
    }

    static String usage() {
        return '''Usage: groovy src/main/groovy/stakeholder/StakeholderCli.groovy [options]

Options:
  --list-values                  Print supported values as JSON and exit.
  --focus-family FAMILY          Emit only one generator family.
  --output-format text|json      Select text or normalized JSON-line output.
  --seed INTEGER                 Stable deterministic seed.
  --experimental-provider [NAME] Fail fast; live providers are not enabled in tranche C.
  --help                         Show this help.'''
    }

    static void fail(String message) {
        System.err.println("error: ${message}")
        System.exit(2)
    }
}
