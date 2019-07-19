# Setup Instructions for Programmers

The first-time setup is a bit involved. Eclipse doesn't make it easy.

## Download the JDK:

OpenJDK 11 is no longer available for download from Oracle. So we have to use an older version from the OpenJDK site itself.

1) Go to https://jdk.java.net/archive

2) Under `11.0.2 (bulId 11.0.2+9)`, click the "zip" download link.

2) Extract the zip file, and place its contents in `C:\Program Files\Java\openjdk-11.0.2`. You may need to rename it.

## Download Gradle:

1) Go to https://gradle.org/releases

2) Under `v5.5.1`, click the "binary-only" download link.

3) Extract the zip file, and place its contents in `C:\Program Files\gradle-5.5.1`.

## Setting up the command line (optional)

1) Open the Windows Control Panel (type "Control Panel" in the Cortana searchbar) and open the "System" panel.

2) In the upper-left corner, click "Advanced system settings."

3) In the `System Properties` window that opens, click the "Environment Variables..." button.

4) In the section labeled `System Variables`, scroll to the variable named "path", "Path", or "PATH" and select it. Click "Edit...".

5) In the `Edit environment variable` window that opens, click the "New" button.

6) Type `C:\Program Files\Java\openjdk-11.0.2\bin` and press Enter.

7) Click the "New" button again, and type `C:\Program Files\gradle-5.5.1\bin`. Press the "Ok" button.

8) In the section labeled `System Variables`, click the "New..." button.

9) For "Variable name", type `JAVA_HOME`. For "Variable value", type `C:\Program Files\Java\openjdk-11.0.2`. Press the "Ok" button.

10) Click the "New..." button again. Enter `GRADLE_HOME` for the name and `C:\Program Files\gradle-5.5.1` for the vaule. Press the "Ok" button.

11) Press "Ok" on all open windows.

- If you have any open Command Prompt or Powershell windows, you won't see the changes. The changes only apply to any window created *after* you save them.

## Setup Eclipse:

1) Go to Window->Preferences->Java->Installed JREs.

2) Click the "Add..." button.

3) Select "Standard VM", and click the "Next >" button.

4) In the JRE home box, type `C:\Program Files\Java\openjdk-11`. The rest of the window will autocomplete.

5) Press the "Finish" button.

6) In the `Preferences` window, check the box next to the new `openjdk-11.0.2` entry. 

## Clone the Project:

1) Look at the `Git Repositories` view.

- If it's not already visible, go to Window->Show View->Other, type "git" in the search box, and select the `Git Repositories` view from the list. You should also probably do the same for the `Git Staging` window.

2) In the top bar of the view, click the "Clone Repository" button. (It has a green arrow.)

3) In the URI field, copy and paste this: `https://github.com/Phlosioneer/AngelsAndDemonsTactics.git`

4) In the `Authentication` section, if it's not already filled in, type your Github username and password.

5) Click the "Next >" button, then click it again.

6) Click the "Finish" button.

## Import the Project:

1) Go to File->Import...

2) Expand the `Gradle` folder and click "Existing Gradle Project"

3) If you see a screen with just info about gradle features (no text boxes), click the "Don't show this again" checkmark and press the "Next >" button.

4) Click the "Browse..." button, and navigate to the AngelsAndDemonsTactics folder. It should be inside your Eclipse's workspace folder (which defaults to C:\Users\<Username>\workspace). With that folder selected, click the "Open" button.

5) Confirm that the `Project root` field reads something like "blah/blah/blah/AngelsAndDemonsTactics". Click the "Next >" button.

6) Click the checkbox next to `Override workspace settings`.

7) In the `Gradle distribution` section, click the circle next to `Local installation directory`.

- Eclipse will have a warning about portability or something. Ignore it.

8) In the text field next to `Local installation directory`, type: `C:\Program Files\gradle-5.5.1`

9) Click the `Finish` button. A loading bar will appear; it may take a minute for progress to be shown as it starts up Gradle.

- The loading bar should not stall at 0% or 1% for more than 5 minutes. If it does, use Task Manager to kill Eclipse, then check that steps 6 and 7 were done correctly. Eclipse is dumb and if it fails to find Gradle, it hangs forever instead of showing an error.

## Create a Run Configuation:

1) In the Eclipse toolbar, locate the "Run" button. It is a large green circle with a white arrow pointing right.

- There's a similar icon with a small red toolbox in the lower right corner. It's not the run button.

2) To the right of the "Run" button, there is a small gray or black arrow pointing down. Click on that arrow, then select `Run Configurations...`.

3) In the list on the left side of the `Run Configurations` window, double-click `Gradle Project`.

4) Under the `Gradle Tasks` section, click the "Add" button.

5) Type `run`, and press Enter. (The whole line should read `run`, not `task run`.)

6) Under the `Working Directory` section, click the `Workspace...` button.

7) Click the `AngelsAndDemonsTactics` entry, then press the "Ok" button.

8) In the lower right of the `Run Configurations` window, press the "Apply" button. Close the window.

Now, to run the project, just press the "Run" button.

## Done!

Finally.
