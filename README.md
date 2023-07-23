# Flexible Arms

A Quilt mod (potentially Fabric & NeoForge too but I don't have time to do a multi-platform setup and I'm too lazy to handle this as well) allowing adjusting arm positions in item JSON models.

This mod is inspired by the great [Blåhaj](https://modrinth.com/mod/blahaj) mod.

### Getting Started

It's pretty simple! You just need to add a few lines into your existing JSON model files.

```json
{
  // ...
  "arm_properties": {
    "held_mainhand": {
      "right_arm": { // Adjust the right arm
        "pitch": -0.95, // Any float value is valid
        "yaw": -0.39, // Here too
        "roll": 0.0,
        "follow_sight": false // Optional - Whether this arm will move according to the holder's sight (Think bows and crossbows)
      },
      "left_arm": { // Left arm too
        "pitch": -0.90,
        "yaw": 0.39,
        "roll": 0.0,
        "follow_sight": false
      }
    },
    "held_offhand": { // Also off hand
      "right_arm": { // Adjust the right arm
        "pitch": -0.95, // Any float value is valid
        "yaw": -0.39, // Here too
        "roll": 0.0,
        "follow_sight": false // Optional - Whether this arm will move according to the holder's sight (Think bows and crossbows)
      },
      "left_arm": { // Left arm too
        "pitch": -0.90,
        "yaw": 0.39,
        "roll": 0.0,
        "follow_sight": false
      }
    }
    // If any field above (except for "follow_sight") is presented,
    // the game will not try to adjust the model with vanilla mechanics.
    // You'd better configure both pitch and yaw for each arm at the same time.
  }
}
```

> JSON does not support comments, you'll need to remove the comments before you put them into a real JSON file!
