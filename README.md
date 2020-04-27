# mini-project-5

## Usage

To start:

```bash
docker run -it --rm \
    -v "$(pwd):/pwd" \
    -p 10334:10334 \
    -p 40404:40404 \
    -p 7575:7575 \
    -p 1099:1099 \
    --hostname localhost \
    apachegeode/geode
```
