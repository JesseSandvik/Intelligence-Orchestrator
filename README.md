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

### Plugin Packages

<i>Plugin Packages</i> are required to follow a specific naming convention.

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

#### Plugin Package Review

All done! Let's have a look at our finished "hello" <i>Plugin Package</i>:

        Plugin Package Directory:    io/plugins/hello/
        Plugin Executable File:      io/plugins/hello/hello
        Plugin Configuration File:   io/plugins/hello/hello.properties

<br />

#### Load The Plugin Package

- <b>[IO]</b> will automatically recognize and integrate all <i>Plugin Packages</i> from the plugins directory.

- Please be sure to follow the naming convention outlined above to ensure that <b>[IO]</b> is able to recognize and integrate your <i>Plugin Packages</i>.

<br />

#### Run The Plugin Package

- <b>[IO]</b> dynamically creates a subcommand for each <i>Plugin Package</i>.

- To run the "hello" <i>Plugin Package</i>:

        io hello

- Let's say the "hello.properties" file includes the "world" parameter:

        io hello world

- Let's say the "hello.properties" file included the "--name|-n" option:

        io hello --name Mark

<br />

#### Removing Features

If a user wanted to remove a feature from <b>[IO]</b>, they can delete the <i>Plugin Package</i> for that feature.

        io/plugins/<plugin-to-delete>/

Alternatively, <b>[IO]</b> comes with a built-in command for deleting existing <i>Plugin Packages</i>.

        io remove --plugin <plugin-name>

<br />

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## History

TODO: Write history

## Credits

TODO: Write credits

## License

TODO: Write license