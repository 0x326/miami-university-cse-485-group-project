# mini-project-5

## Usage

To start:

```bash
cd miami-university-cse-485-group-project/

docker run -it --rm \
    -v "$(pwd):/pwd" \
    -p 10334:10334 \
    -p 40404:40404 \
    -p 7575:7575 \
    -p 1099:1099 \
    --hostname localhost \
    apachegeode/geode

# Then, copy and past gfsh_init.sh into the GFSH prompt
```
