# Profiling

```
python -m cProfile script.py
```

Check out `snakeviz` - https://jiffyclub.github.io/snakeviz/

# Pipenv

https://github.com/pypa/pipenv/blob/main/docs/configuration.md

`PIPENV_IGNORE_VIRTUALENVS=1` can be used when trying to run on an environment that already uses virtualenv. The symptoms of this is that using pipenv or virtualenv yourself will still cause pip3 to be the one from the other environment, so this forces a separation to avoid conflicts.
