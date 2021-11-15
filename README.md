#KeyLoop

Mod that provides spam clicking/holding a certain minecraft keybinding

Original idea and some source code was taken from [TapeMouse](https://github.com/dries007/TapeMouse) as it's author stopped maintaining it
#How to
###`/keyloop list`
Command displays all available keybindings
You can hover over keybind and click on it to suggest command

###`/keyloop <bind> <delay>`
If delay equals 0 bind will be **held**
Command autocompelete supports every available keybind for `<bind>` and ticks, seconds, day for `<delay>`

###`/keyloop off`
Disables keybind

#Config
```textRenderEnabled``` - defines will be overlay additional info rendered at top left corner

#Warnings
- So far this mod is easy detectable don't use on servers where is not allowed AFK farming
- if you run command immediately after server connection, it won't work. Click necessary key and then use command
- Some commands such as `jump`, `forward`, `right`, etc. don't support delay more than 0. I don't exactly know is it Minecraft limitations or not, but I can't fix it for now

#Q&A
###**1.7.10/1.12.2 backports?**

Maybe, if I have enough time and motivation to write on older versions
###**Fabric version**

No

#Contribution
If you find a bug, want to suggest an improvement or have a question, you can do it in **issue** section above