import os
import shutil
from pathlib import Path


def groovy_command(*args):
    executable = shutil.which("groovy")
    if executable is None:
        raise RuntimeError("groovy executable is required")

    launcher = Path(executable)
    if os.name != "nt" or launcher.suffix.lower() not in {".bat", ".cmd"}:
        return [str(launcher), *map(str, args)]

    groovy_home = Path(os.environ.get("GROOVY_HOME", launcher.parent.parent))
    java = shutil.which("java")
    if java is None:
        raise RuntimeError("java executable is required for the Windows Groovy launcher")
    return [java, "-cp", str(groovy_home / "lib" / "*"), "groovy.ui.GroovyMain", *map(str, args)]
