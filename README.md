[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2FChameleonFramework%2FChameleon.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2FChameleonFramework%2FChameleon?ref=badge_shield)

<div align="center">
    <a href="#logo"><img src="https://i.hypera.dev/assets/chameleon@750x150.png" /></a>
    <p><strong>Cross-platform Minecraft plugin framework</strong></p>
</div>

-----------
<div align="center">
   <img alt="GitHub license" src="https://img.shields.io/badge/License-MIT%20LICENSE-%2317aaaa?style=for-the-badge">
   <img alt="Libraries.io dependency status for GitHub repo" src="https://img.shields.io/librariesio/github/ChameleonFramework/Chameleon?color=%2317aaaa&style=for-the-badge"><br />
   <img alt="GitHub code quality" src="https://img.shields.io/codefactor/grade/github/ChameleonFramework/Chameleon/main?style=for-the-badge&color=%2317aaaa">
   <img alt="GitHub code size in bytes" src="https://img.shields.io/github/languages/code-size/ChameleonFramework/Chameleon?color=%2317aaaa&style=for-the-badge">
   <img alt="Snyk Vulnerabilities for GitHub Repo" src="https://img.shields.io/snyk/vulnerabilities/github/ChameleonFramework/Chameleon?color=%2317aaaa&style=for-the-badge">
</div>


[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2FChameleonFramework%2FChameleon.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2FChameleonFramework%2FChameleon?ref=badge_large)

### What is Chameleon?
Chameleon is a framework created to aid in the creation of cross-platform Minecraft plugins.


### Supported Platforms
Chameleon plans to support every platform in this list. If you have ideas for other platforms that we could support, please suggest them in our [Official Discord Server][Discord].
 - [x] [BungeeCord]
 - [x] [Spigot]
 - [x] [Velocity]
 - [x] [Minestom]
 - [ ] [Sponge]
 - [ ] [Nukkit]

### How do I use Chameleon?
As this is hard to explain, we have created an [Example Project][Example]. In short, you have a common module with non-platform-specific code (this would only be Chameleon code), then you have a module for each platform you would like to support.  
You can also check out our [Development Roadmap][Roadmap] or various examples of [BungeeCord support][platform-bungeecord], [Spigot support][platform-spigot], [Velocity support][platform-velocity] and [Minestom support][platform-minestom] for reference.

### Project Structure
 * **Core** - Most of Chameleon's non-platform-specific code belongs here.
 * **Features** - Features that are not deemed to be a core part of Chameleon.
   * *Configuration* - YAML and JSON configuration file loaders. 
 * **Platforms** - Any platform-specific code belongs inside here.
   * *BungeeCord* - Any code that interacts with BungeeCord belongs here.
   * *Minestom* - Any code that interacts with Minestom belongs here.
   * *Spigot* - Any code that interacts with Spigot belongs here.
   * *Velocity* - Any code that interacts with Velocity belongs here.


### Contributing
Please read [CONTRIBUTING].

### License
The contents of this repository is licensed under the [MIT License](LICENSE).


[BungeeCord]: https://www.spigotmc.org/wiki/bungeecord/
[Spigot]: https://www.spigotmc.org/
[Velocity]: https://velocitypowered.com/
[Minestom]: https://www.minestom.net/
[Sponge]: https://www.spongepowered.org/
[Nukkit]: https://github.com/CloudburstMC/Nukkit
[Example]: https://github.com/ChameleonFramework/Example
[Roadmap]: DEVELOPMENT.md
[Discord]: https://discord.hypera.dev/
[platform-bungeecord]: platform-bungeecord/
[platform-spigot]: platform-spigot/
[platform-velocity]: platform-velocity/
[platform-minestom]: platform-minestom/
[CONTRIBUTING]: CONTRIBUTING.md