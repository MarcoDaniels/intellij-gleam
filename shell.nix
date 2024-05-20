let
  pkgs = import (fetchTarball {
    name = "nixpkgs-23.11-darwin";
    url = "https://github.com/NixOS/nixpkgs/archive/8a4282c38b6c.tar.gz";
    sha256 = "1bzp6yi7bz2r4pkhb3mdfdzc2vv26jsbwb04a6mx9l25xzvpfnxp";
  }) { };
in pkgs.mkShell {
  packages = with pkgs; [ sbt scala jdk17 gleam ];

  shellHook = "";
}
