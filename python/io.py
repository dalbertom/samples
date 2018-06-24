from pathlib import Path
with open(str(Path.home()) + '/.github-token') as f:
  read_data = f.read()

print(read_data.strip())
