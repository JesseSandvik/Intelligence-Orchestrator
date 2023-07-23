# Intelligence Orchestrator [IO]

TODO: Write a product description

## Installation

TODO: Write installation instructions

## Usage

<b>[IO]</b> dynamically adds features as subcommands:

        io <my-feature>

Users can develop their own features as standalone executables, and seamlessly integrate their features into <b>[IO]</b>:

        io do-something-cool

        [IO] I did something cool!

For <b>[IO]</b> to recognize and integrate a feature, a <i>Plugin Package</i> must be added to the plugins directory:

        io/plugins/<plugin-package-name>/

<br />
<br />

### Plugin Packages

- <i>Plugin Packages</i> encapsulate all of the behavior and information for a standalone feature. 

- <i>Plugin Packages</i> are required to follow a specific naming convention.

<br />

Let's create a <i>Plugin Package</i> named "hello":

        Plugin Package Directory: io/plugins/hello/

A <i>Plugin Package</i> requires <b>two</b> files:

1. #### Plugin Executable File

    - A standalone executable file that <b>[IO]</b> will run as a subcommand.

   - The name of the executable file will become the subcommand.

   - The "hello" plugin executable file in the "hello" <i>Plugin Package</i>:

            Plugin Executable File: io/plugins/hello/hello

2. #### Plugin Configuration File

    - A configuration file containing the metadata for the plugin.
      - Metadata includes, but is not limited to:
        - plugin version (required)
        
        - plugin description (required)
        
        - plugin parameters (optional)
        
          - description for each parameter (required for each parameter, when a parameter has been provided)
        
        - plugin options (optional)

          - long and short (required for each option, when an option has been provided)

          - description for each option (required for each option, when an option has been provided)

   - Plugin configuration files must have the suffix ".properties".

   - The name of the configuration file must match the name of the executable file.

   - The "hello" plugin configuration file in the "hello" <i>Plugin Package</i>:

            Plugin Configuration File: io/plugins/hello/hello.properties

<br />
<br />

Before we run our "hello" <i>Plugin Package</i> through <b>[IO]</b>, let's take a look at what we have so far:

        Plugin Package Directory:    io/plugins/hello/
        Plugin Executable File:      io/plugins/hello/hello
        Plugin Configuration File:   io/plugins/hello/hello.properties

<br />
<br />

#### Load The Plugin Package

- <b>[IO]</b> will automatically recognize and integrate all <i>Plugin Packages</i> from the plugins directory.

- Please be sure to follow the naming convention outlined above to ensure that <b>[IO]</b> is able to recognize and integrate your <i>Plugin Packages</i>.

<br />
<br />

#### Run The Plugin Package

- <b>[IO]</b> dynamically creates a subcommand for each <i>Plugin Package</i>.

- To run the "hello" <i>Plugin Package</i>:

        io hello

- If the "hello.properties" file included an optional parameter named "world":

        io hello world

- If the "hello.properties" file included an optional option named "--person|-p":

        io hello --person Mark

<br />
<br />

#### Plugin Package Logging

- <b>[IO]</b> performs logging automatically for all <i>Plugin Packages</i> when they are executed.

- Logs for a <i>Plugin Package</i> can be found in the logs directory of that <i>Plugin Package</i>:

        Plugin Package Log Directory: io/plugins/hello/logs

- Let's have a look at our "hello" <i>Plugin Package</i> after it has been run as a subcommand for <b>[IO]</b>:

        Plugin Package Directory:      io/plugins/hello/
        Plugin Executable File:        io/plugins/hello/hello
        Plugin Configuration File:     io/plugins/hello/hello.properties
        Plugin Package Log Directory:  io/plugins/hello/logs
        Plugin Package Log File:       io/plugins/hello/logs/log.YYYY-MM-DD.HH-MM-SS

- By default, log files older than 30 days will be deleted when the associated <i>Plugin Package</i> has been executed.

    - This behavior can be modified with the built-in <b>[IO]</b> command:

            io set --plugin hello --feature log --expire 10d10h10s

    - This feature can also be disabled:

            io set --plugin hello --feature log --expire disable

    - To enable this feature after it has been disabled:

            io set --plugin hello --feature log --expire enable

    - Enabling this feature after it has been disabled will set the log expiration back to the last defined value. If a user had manually set the log expiration for the <i>Plugin Package</i> to "10d10h10s" prior to the log expiration being manually disabled, upon manually enabling log expiration again, the log expiration for the <i>Plugin Package</i> would be set back to "10d10h10s".
    - If a user had never manually set the log expiration for the <i>Plugin Package</i> prior to the log expiration being manually disabled, upon manually enabling log expiration again, the log expiration for the <i>Plugin Package</i> would be set to the <b>[IO]</b> default value of 30 days.

- To manually reset the log expiration for a <i>Plugin Package</i> back to the <b>[IO]</b> default value of 30 days:

        io set --plugin hello --feature log --expire default

<br />
<br />

#### Plugin Package Local Logging

- <b>[IO]</b> can distribute logs to a directory of your choosing on your local machine.

- The distributed logs will be a duplication of the logs from the <b>[IO]</b> <i>Plugin Package</i> logs directory.

- There is no expiration for distributed logs. These logs exist outside of the scope of <b>[IO]</b>, and after being generated, are never referenced or changed by <b>[IO]</b>.

- This feature is disabled by default, but can be enabled by using the built-in <b>[IO]</b> command:

      io set --plugin hello --feature local-log --directory ~/i-want-my-logs-here/

- The specified directory will become the parent directory for the <i>Plugin Package's</i> local log directory:

        Plugin Package Local Log Directory: ~/i-want-my-logs-here/hello-logs/

<br />
<br />

#### Removing Plugin Packages

- If a user wanted to remove a feature from <b>[IO]</b>, they can delete the <i>Plugin Package</i> for that feature.

        io/plugins/<plugin-to-delete>/

- Alternatively, <b>[IO]</b> provides a built-in command for deleting existing <i>Plugin Packages</i>.

        io remove --plugin <plugin-name>

- Before <b>[IO]</b> will delete a <i>Plugin Package</i>, it will prompt the user for confirmation:

        Are you sure you want to permanently delete the Plugin Package: <plugin-name>? (YES/NO)

  - User input is case-sensitive and must be "YES" or "NO".
  - "YES" will permanently delete the <i>Plugin Package</i> from the plugins directory.
  - "NO" will not make any changes.

<br />
<br />

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## History

TODO: Write history

## Credits

TODO: Write credits

## License

TODO: Write license