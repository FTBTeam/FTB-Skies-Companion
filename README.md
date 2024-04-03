# FTB Skies Companion

Skies companion is a relatively small mod that adds a couple of helper tools to the FTB Skies series of modpacks.

## Features

So far, we have one major feature...

- Crystallization
    - Adds a new crafting mechanic to create that allows ingredient to be mixed with mana to produce a new resource.
    - This can be done by linking a mana source to the block and providing it with the correct input


## Preview

![A preview of what the crystallization machine looks like](https://cdn.feed-the-beast.com/mods/ftb-skies-companion/crystallization-machine.png) ![A preview of the JEI recipe](https://cdn.feed-the-beast.com/mods/ftb-skies-companion/crystallization-jei.png)

## Recipes

Here is a preview of what the recipe should look like

```json
{
    "type": "ftbskies_companion:crystallization",
    "input": {
        "ingredient": {
            "fluid": "minecraft:water"
        },
        "amount": 1000
    },
    "mana": 200,
    "processTime": 200,
    "output": {
        "id": "minecraft:ice"
    }
}
```
