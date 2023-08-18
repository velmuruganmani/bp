# bp
## Running Spring/SpringBoot + Thymeleaf + PostgreSQL on CoderPad

This pad is running a SpringBoot application configured to connect to a PostgreSQL database. It uses the ORM tool Hibernate to make it easy to interact with the database. Changes are automatically compiled as you type them, and you can add as many files to the project as you need. To see your changes reflected on the iframe, after compilation is complete refresh the iframe.

To get started, edit the `src/main/java/codinpad/controller/EmployeeController.java` file, and query your API to see the response.

### Accessing your API

We've installed `curl` in the container running your application, so you can make requests to your API directly from the Shell. For instance, you can run `curl 127.0.0.1:3000/employees` to see your server's output. You can also go to the `/directory` page to see a basic table of the example employee data.

### IntelliSense

IntelliSense is running across your entire project, allowing you to see when there are syntax errors or to get quick hints for how to resolve errors or TypeScript issues.

### Shell

A shell is provided to you so you can inspect your container in more detail. The shell can be used to run maven commands with `mvn`

**Note: while it's possible to edit files directly from the shell, we recommend using the editor to make your changes. That way, other people in the Pad can see your changes as they're being made.**

### Dependencies

The dependencies are listed in the `pom.xml` file.

### Configuration

The configuration for the application is in the `src/main/java/resources/application.yml` file.

### Database creation

The database is an empty database that is auto populated by the application.

### Container Limits

The container running your application has a few limitations. Currently, we don't limit your CPU usage, though this may change in future. In addition to CPU, we monitor the network bandwidth that is consumed, and limit you to 75mb for the duration of the container. Finally, we limit the amount of memory accessible to each container to 0.5 GB.
