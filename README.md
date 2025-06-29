# LegacyBossbar

LegacyBossbar is a powerful, thread-safe 1.8 BossBar API designed to display a BossBar in Minecraft clients by sending fake entity packets, Legacy-BossBar allows developers to bypass current 1.8.8 BossBar restrictions and use the the feature flawlessy to display information

## Features
- **Broad Compatibility**: Built for Spigot 1.8.8, Works seamlessly with Minecraft clients below 1.8
- **No Restrictons**: Featuring unique hacks to bypass current bossbar restrictions; Say goodbyte to the unwanted particle effects when changing the health display!
- **Lightweight**: Uses its own dedicated thread for minimal performance impact on the server.
- **Developer-Friendly**: Easy-to-use API for integrating with existing plugins or projects.
---

![image](https://github.com/user-attachments/assets/1c9fa838-ccbe-48f6-8751-945a2dc87edf)

## Installation
1. Download the latest version of the Legacy-BossBar jar from the [Releases](#).
2. Add the jar to your project's dependencies.
3. Import the API into your codebase and start customizing your tab list.

---

## Usage
To start using Legacy-Tab, you simply need to initialize the API and send your desired bossbar adapter. Here's a basic example:

Initialize with your plugin's instance:
```java
// Initialize LegacyBossBar in your constructor
  LegacyBossBar legacyBossBar = new LegacyBossBar(plugin);
```

Create a BossBar Adapter (Also supports anonymous classes)
```java
public class ExampleAdapter extends BossBarAdapter {

    private int health = 100;

    @Override
    public void tick() {
        if (--health <= 0)
            health = 100;
    }

    @Override
    public String getTitle(Player player) {
        return ChatColor.translateAlternateColorCodes('&',
                ("&" + Math.floor(Math.random() * 10)) + UUID.randomUUID().toString());
    }

    @Override
    public float getHealth(Player player) {
        return health;
    }

    @Override
    public boolean isHidden(Player player) {
        return false;
    }
}
```

Set a player's BossBar Adapter (Using your instance of LegacyBossBar)
```java
  Player player = event.getPlayer();
  BossBarAdapter bossBarAdapter = new ExampleAdapter();

  legacyBossBar.setAdapter(player, bossBarAdapter);
```

---
