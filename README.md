# IBM Bob: Java Modernization Labs

Hands-on labs that use **IBM Bob** and the **IBM Application Modernization Accelerator (AMA)** to modernize Java applications. You replatform a traditional WebSphere application to Liberty, then modernize a legacy Struts application into a Spring Boot backend and a React frontend.

## Labs

| Lab | Title | What you do |
|-----|-------|-------------|
| [Lab 0](lab-guides/lab_0.md) | Configure the Techzone Environment | Reserve a Techzone VM and install IBM Bob, IBM AMA and the project repository. At the end, the AMA UI is running inside the VM. |
| [Lab 1](lab-guides/lab_1.md) | Creating the Migration Bundle | Create a workspace in AMA and run the Discovery Tool against the `ModResorts` sample application. This produces a migration bundle that targets WebSphere Liberty. |
| [Lab 2](lab-guides/lab_2.md) | Modernizing a Traditional WebSphere Application to Liberty with Bob | Use Bob's **Java Modernization** workflow to read the AMA migration bundle and apply OpenRewrite recipes. Bob then fixes Liberty-specific issues, and you deploy `ModResorts` to a local Open Liberty server. |
| [Lab 3](lab-guides/lab_3.md) | Full Application Modernization | Use Bob's **UI Modernization** workflow on the `legacy-netbanking` application. Bob migrates the backend to Spring Boot and the frontend to React with the Carbon Design System. You verify the result in the browser. |

Labs 0 to 2 build on each other, so do them in order. Lab 3 works on a different application, but it assumes the Lab 0 environment and Bob setup.

## Repository Layout

| Path | Description |
|------|-------------|
| `lab-guides/` | Lab instructions (`lab_0.md` to `lab_3.md`) and their screenshots in `lab-guides/images/`. |
| `java-liberty-replatforming-websphere/` | `ModResorts` sample application and its AMA migration bundle, used in Labs 1 and 2. |
| `java-liberty-replatforming-tomcat/` | `ModResorts` variant for Tomcat, with its own migration bundle. |
| `legacy-netbanking/` | Legacy NetBanking application that you modernize in Lab 3. |

## Prerequisites

- IBM Bob IDE.
- A Bob account entitled to the **Java Modernization Premium Package**.
- Enough BobCoins for the workflows. The earlier version of the lab guide estimated about 100.
- Access to the Techzone environment from Lab 0, or an equivalent local setup with Java, Maven and AMA.

## Key Takeaways

- AMA discovers an application's technology stack and produces a migration bundle.
- Bob's **Java Modernization** workflow handles Liberty replatforming and Java version upgrades.
- Bob's **UI Modernization** workflow splits a legacy web application into a REST backend and a modern frontend.
