# Lab 02: Modernizing a Traditional WebSphere Application to Liberty with Bob

> **Prerequisites:** Complete [Lab 00](lab_0.md) and [Lab 01](lab_1.md). The project repository should be cloned at `~/Downloads/BOB_AMA/ibm-ama-bob-liberty-replatforming` and the AMA migration bundle should be available at `java-liberty-replatforming-websphere/migration-bundle/modresorts.ear_migrationBundle.zip`.

---

## Overview

In this lab you will use **IBM Bob** to run the **Java Modernization** workflow, which reads the AMA migration bundle, applies OpenRewrite recipes, fixes Liberty-specific issues, and deploys the replatformed `ModResorts` application to a local Open Liberty server.

---

## Step 1: Launch IBM Bob and Open the Project

1. Open a Terminal in the VM and navigate to the project directory, then launch the Bob IDE:

   ```bash
   cd ~/Downloads/BOB_AMA/ibm-ama-bob-liberty-replatforming
   ```

   ```bash
   bobide .
   ```

   ![Launch Bob IDE](images/lab_2_1.png)

2. If prompted to create a password for a new keyring, leave the field blank and click **Continue**.

   ![Create Keyring Password](images/lab_2_2.png)

3. On the **Welcome to Bob** setup screen asking to import settings and extensions, click **Skip for now**.

   ![Welcome to Bob — Skip Setup](images/lab_2_3.png)

4. The Bob IDE opens with the project directory in the Explorer view. Click **Manage** on the **Restricted Mode** banner at the top.

   ![Bob IDE — Restricted Mode Banner](images/lab_2_4.png)

5. When prompted with the **Workspace Trust** dialog, click **Trust** to enable all features.

   ![Workspace Trust Dialog](images/lab_2_5.png)

6. Click the **Login** button in the Bob chat window.

7. If a chat migration dialog appears stating *"Bob v1.0.0 chats were created in an older version of Bob"*, click **Skip migration** to proceed.

   ![Chat Migration Dialog](images/lab_2_6.png)

8. The Bob IDE loads with the **Hi, I'm Bob** assistant pane on the right. Click **Log in to Bob** to authenticate.

   ![Bob IDE Welcome — Log In](images/lab_2_7.png)

9. A notification appears at the bottom right: *"You are entitled to use the Premium Package for Java. Would you like to install this now?"* Click **Install**.

   ![Premium Package Install Notification](images/lab_2_8.png)

10. A trust dialog asks *"Do you trust the publisher 'IBM'?"* Click **Trust Publisher & Install** to install the **IBM Bob Premium Package for Java Modernization** extension.

    ![Trust Publisher Dialog](images/lab_2_9.png)

11. Once installed, the **Welcome — IBM Bob for Java** tab opens, showing available workflows including **Liberty Modernization**, **Java Upgrade**, and **UI Modernization**. The extension is now ready to use.

    ![IBM Bob for Java — Welcome Tab](images/lab_2_10.png)

---

## Step 2: Start the Java Modernization Workflow

12. In the Bob chat panel, click the **Workflows** icon to open the **Bob Workflows** panel. Click **Start** next to **Java Modernization**.

    ![Bob Workflows Panel](images/lab_2_11.png)

13. The **Java Modernization** workflow opens on the **Analyze Project** step. Under **Select Project**, choose the WebSphere project path:

    ```
    /home/itzuser/Downloads/BOB_AMA/ibm-ama-bob-liberty-replatforming/java-liberty-replatforming-websphere
    ```

    Leave **Custom build command** disabled and click **Continue**.

    ![Java Modernization — Analyze Project](images/lab_2_12.png)

    > **Note:** Throughout the workflow, Bob will request approval for tool executions (file edits, Maven commands, git operations, and todo list updates). Click **Approve for task** (or **Approve once**) whenever prompted to proceed.

14. Bob begins analysing the project, detecting dependencies, running vulnerability checks, and building it. Approve the requested tools to proceed.

    ![Analyze Project — Query Vulnerabilities Approval](images/lab_2_13.png)

    ![Analyze Project — Build Approval](images/lab_2_14.png)

15. Once analysis is complete, Bob moves to the **Flow Selection** step. Select **Liberty Modernization**, ensure **Enable Git Flow** is toggled on, and click **Continue**.

    ![Flow Selection — Liberty Modernization](images/lab_2_15.png)

16. Bob opens the **Select AMA Zip** step. Click **Select File** under **AMA Zip Path**.

    ![Select AMA Zip — File Picker](images/lab_2_16.png)

17. In the file dialog, navigate to:

    ```
    Downloads → BOB_AMA → ibm-ama-bob-liberty-replatforming → java-liberty-replatforming-websphere → migration-bundle
    ```

    Select **modresorts.ear_migrationBundle.zip**, click **Select File**, then click **Continue**.

    ![Open File Dialog — migration-bundle directory](images/lab_2_17.png)

    ![AMA Zip Selected — Continue](images/lab_2_18.png)

18. Bob analyses the AMA zip and prepares the Liberty configuration. Approve the prompts to write `src/main/liberty/config/server.xml` and the `Containerfile`.

    ![Analyze AMA Zip — Write server.xml Approval](images/lab_2_19.png)

    ![Analyze AMA Zip — Write Containerfile Approval](images/lab_2_20.png)

---

## Step 3: Run OpenRewrite Recipes and Fix Liberty Issues

19. Bob runs the Liberty-specific OpenRewrite recipes (`RemoveWas2LibertyNonPortableJndiLookup`, `WebSphereUnavailableSSSOMethods`, `ServerName`). Approve the tool execution to run the recipes.

    ![Run OpenRewrite Recipes — Approval](images/lab_2_21.png)

20. Bob moves to the **Replatform Liberty Issues** step to fix critical rule violations. Approve the subtask execution to start **Liberty Modernization subtask #1** (*Fix "The WebSphere Runtime APIs and SPIs are unavailable" rule*).

    ![Replatform Liberty Issues — Start Subtask #1](images/lab_2_22.png)

21. Bob analyses `WeatherServlet.java` and `env-config` dependencies, identifying the need to upgrade `env-config`. When prompted, select **Yes, upgrade env-config from 1.5 to 1.7 in pom.xml (latest available)**.

    ![Subtask #1 — Approve env-config Upgrade](images/lab_2_23.png)

22. Bob applies the update to `pom.xml`, compiles the project (`mvn compile`), and packages the WAR (`mvn package`) to verify the fix. Approve the prompts as Bob completes verification and finishes the subtask.

    ![Subtask #1 — Complete Subtask Approval](images/lab_2_24.png)

23. Back in the main workflow, Bob starts **Liberty Modernization subtask #2** (*Fix "The WebSphere Servlet API was superseded by a newer implementation" rule*). Approve the tool prompt to launch the subtask.

    ![Replatform Liberty Issues — Start Subtask #2](images/lab_2_25.png)

24. Bob analyses `UpperServlet.java` and identifies proprietary `ResponseUtils.encodeDataString()` usage. When prompted, select **Yes, proceed with replacing ResponseUtils.encodeDataString() with StringEscapeUtils.escapeHtml4() and adding commons-text:1.12.0 to pom.xml**.

    ![Subtask #2 — Proposed Changes Confirmation](images/lab_2_26.png)

25. Bob updates `pom.xml`, modifies `UpperServlet.java`, and compiles to verify success. Approve the completion summary to finish the subtask and return to the main workflow.

    ![Subtask #2 — Complete Subtask Approval](images/lab_2_27.png)

---

## Step 4: Deploy the Application Locally

26. In the **Deploy** step, click **Start local deployment** and approve starting the Deploy subtask.

    ![Deploy Step — Start Local Deployment](images/lab_2_28.png)

    ![Deploy Step — Execute Deploy Subtask Approval](images/lab_2_29.png)

27. The Deploy subtask builds the WAR, adds the Liberty Maven plugin to `pom.xml`, creates the Liberty server (`mvn liberty:create`), installs features (`mvn liberty:install-feature`), starts the server, and validates the logs. Approve the tool prompts as Bob executes each step.

    ![Deploy Subtask — Add Liberty Plugin to pom.xml](images/lab_2_30.png)

28. Open [http://localhost:9080/resorts](http://localhost:9080/resorts) in your browser to verify the ModResorts application is running on Open Liberty.

    ![ModResorts Running on Open Liberty](images/lab_2_31.png)

29. In Bob IDE, when asked *"Did the application start successfully, or do you see warnings or errors in the logs?"*, click **Yes, the application started successfully with no errors.**

    ![Deploy Subtask — Deployment Success Confirmation](images/lab_2_32.png)

30. The modernization workflow finishes with **All tasks completed!** and displays a full summary of all identified issues, code transformations, and the verified Liberty deployment.

    ![Java Modernization Workflow — All Tasks Completed](images/lab_2_33.png)

    ![Modernization Summary Diagram](images/lab_2_34.png)

---

*You have successfully replatformed the ModResorts application from traditional WebSphere to Open Liberty using IBM Bob.*
