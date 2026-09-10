#!/usr/bin/env python3
"""Copy the binary search tree Java sources to a target directory."""

import argparse
import shutil
from pathlib import Path

SOURCE_FILES = (
    "BinarySearchTree.java",
    "BinaryNode.java",
    "SortedCollection.java",
)


def export_sources(source_dir: Path, target_dir: Path) -> None:
    """Copy the required Java source files into target_dir."""
    target_dir.mkdir(parents=True, exist_ok=True)

    for filename in SOURCE_FILES:
        source_file = source_dir / filename
        if not source_file.is_file():
            raise FileNotFoundError(f"Missing source file: {source_file}")

        target_file = target_dir / filename
        shutil.copy2(source_file, target_file)
        print(f"Wrote {target_file}")


def main() -> None:
    """Parse command-line arguments and export the Java sources."""
    parser = argparse.ArgumentParser(
        description="Copy the binary search tree Java sources to a target directory."
    )
    parser.add_argument(
        "target",
        type=Path,
        help="Directory where the .java files will be written",
    )
    parser.add_argument(
        "--source",
        type=Path,
        default=Path(__file__).resolve().parent,
        help="Directory containing the Java source files (default: this script's directory)",
    )
    args = parser.parse_args()

    export_sources(args.source.resolve(), args.target.resolve())


if __name__ == "__main__":
    main()
