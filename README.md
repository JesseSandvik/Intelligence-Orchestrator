# Intelligence Orchestrator [IO]

TODO: Write a product description

## Installation

TODO: Write installation instructions

## Usage

TODO: Write usage instructions

### Plugin Packages

A Plugin Package requires only two files:

1. #### Plugin Executable File

    - An executable file that <b>[IO]</b> will run as a subcommand.

    - The name of the executable file will be the subcommand for <b>[IO]</b>.

      - Example: An executable file named "helloWorld" would run as a subcommand by running: 

              io helloWorld

2. #### Plugin Configuration File

    - A configuration file containing the metadata for the plugin.

  - Plugin configuration files must have the suffix ".properties".

  - The name of the configuration file must match the name of the executable file.

Plugin Packages dynamically add features as subcommands to <b>[IO]</b>.

        io <my-feature>

Users can develop their own features as standalone executables, and seamlessly integrate these features into <b>[IO]</b>.

        io do-something-cool

        [IO] I did something cool!

If a user wanted to remove a feature from <b>[IO]</b>, they can delete the Plugin Package for that feature.

        io/plugins/<plugin-to-delete>/

Alternatively, <b>[IO]</b> comes with a built-in command for deleting existing Plugin Packages.

        io remove --plugin <plugin-name>

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