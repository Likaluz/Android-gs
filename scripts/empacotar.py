"""Monta um ZIP portátil do projeto, documentação e APK de demonstração."""
from pathlib import Path
from zipfile import ZipFile, ZIP_DEFLATED
import hashlib
import shutil

root = Path(__file__).resolve().parents[1]
out = root / 'output'
out.mkdir(exist_ok=True)
apk = root / 'app/build/outputs/apk/debug/app-debug.apk'
if not apk.exists():
    raise SystemExit('Compile primeiro: gradlew.bat assembleDebug')
shutil.copy2(apk, out / 'BairroVivo.apk')
files = [root / name for name in (
    'README.md', '.gitignore', 'settings.gradle.kts', 'build.gradle.kts',
    'gradle.properties', 'gradlew', 'gradlew.bat', 'app/build.gradle.kts',
)]
for directory in ('app/src', 'gradle/wrapper', 'docs', 'scripts'):
    files.extend(p for p in (root / directory).rglob('*') if p.is_file() and '__pycache__' not in p.parts)
archive = out / 'BairroVivo-Android-RM553304.zip'
with ZipFile(archive, 'w', ZIP_DEFLATED) as z:
    for path in sorted(files):
        z.write(path, 'BairroVivo/' + path.relative_to(root).as_posix())
    z.write(out / 'BairroVivo.apk', 'BairroVivo/apk/BairroVivo.apk')
with ZipFile(archive) as z:
    assert z.testzip() is None
    assert 'BairroVivo/gradle/wrapper/gradle-wrapper.jar' in z.namelist()
    assert not any('/.tools/' in n or '/local.properties' in n for n in z.namelist())
for path in (archive, out / 'BairroVivo.apk'):
    digest = hashlib.sha256(path.read_bytes()).hexdigest()
    print(f'{path.name} | {path.stat().st_size:,} bytes | SHA256 {digest}')
