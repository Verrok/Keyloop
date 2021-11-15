# KeyLoop

Mod that provides spam clicking/holding a certain minecraft keybinding

Works **only** on client-side

Original idea and some of the source code was taken from [TapeMouse](https://github.com/dries007/TapeMouse) as it's author stopped maintaining it
# How to
### `/keyloop list`
Command displays all available keybindings

You can hover over keybind and click on it to suggest command

![image](https://user-images.githubusercontent.com/26070304/141831414-f55cf569-125c-44e0-8b83-58db7c5a38a7.png)

### `/keyloop <bind> <delay>`
If delay equals 0 bind will be **held**
Command autocompelete supports every available keybind for `<bind>` and ticks, seconds, day for `<delay>`

### `/keyloop off`
Disables keybind

# Config
```textRenderEnabled``` -defines if an overlay with an additional info will be rendered at top left corner

![image](https://user-images.githubusercontent.com/26070304/141831508-a629ad9e-6820-4e07-bf85-f6f390e524c6.png)

# Warnings
- As far as this mod is easy detectable, don't use on servers where is not allowed AFK farming
- If you run command immediately after a server connection, it won't work. Click the required key and then use a command
- Some commands such as `jump`, `forward`, `right`, etc. don't support delay more than 0. I don't exactly know if it is Minecraft limitations or not, but I can't fix it for now

# Q&A
### **1.7.10/1.12.2 backports?**

Maybe, if I have enough time and motivation to write on older versions
### **Fabric version**

No

# Contribution
If you find a bug, want to suggest an improvement or have a question, you can do it in **issue** section above
