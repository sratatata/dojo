# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.2

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list

# Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/lb_lb/Projects/dojo/2016/cmake/hello-world

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/lb_lb/Projects/dojo/2016/cmake/hello-world

# Include any dependencies generated for this target.
include CMakeFiles/hello.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/hello.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/hello.dir/flags.make

CMakeFiles/hello.dir/helloworld.cpp.o: CMakeFiles/hello.dir/flags.make
CMakeFiles/hello.dir/helloworld.cpp.o: helloworld.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /home/lb_lb/Projects/dojo/2016/cmake/hello-world/CMakeFiles $(CMAKE_PROGRESS_1)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object CMakeFiles/hello.dir/helloworld.cpp.o"
	/usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/hello.dir/helloworld.cpp.o -c /home/lb_lb/Projects/dojo/2016/cmake/hello-world/helloworld.cpp

CMakeFiles/hello.dir/helloworld.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/hello.dir/helloworld.cpp.i"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /home/lb_lb/Projects/dojo/2016/cmake/hello-world/helloworld.cpp > CMakeFiles/hello.dir/helloworld.cpp.i

CMakeFiles/hello.dir/helloworld.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/hello.dir/helloworld.cpp.s"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /home/lb_lb/Projects/dojo/2016/cmake/hello-world/helloworld.cpp -o CMakeFiles/hello.dir/helloworld.cpp.s

CMakeFiles/hello.dir/helloworld.cpp.o.requires:
.PHONY : CMakeFiles/hello.dir/helloworld.cpp.o.requires

CMakeFiles/hello.dir/helloworld.cpp.o.provides: CMakeFiles/hello.dir/helloworld.cpp.o.requires
	$(MAKE) -f CMakeFiles/hello.dir/build.make CMakeFiles/hello.dir/helloworld.cpp.o.provides.build
.PHONY : CMakeFiles/hello.dir/helloworld.cpp.o.provides

CMakeFiles/hello.dir/helloworld.cpp.o.provides.build: CMakeFiles/hello.dir/helloworld.cpp.o

# Object files for target hello
hello_OBJECTS = \
"CMakeFiles/hello.dir/helloworld.cpp.o"

# External object files for target hello
hello_EXTERNAL_OBJECTS =

hello: CMakeFiles/hello.dir/helloworld.cpp.o
hello: CMakeFiles/hello.dir/build.make
hello: CMakeFiles/hello.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --red --bold "Linking CXX executable hello"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/hello.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/hello.dir/build: hello
.PHONY : CMakeFiles/hello.dir/build

CMakeFiles/hello.dir/requires: CMakeFiles/hello.dir/helloworld.cpp.o.requires
.PHONY : CMakeFiles/hello.dir/requires

CMakeFiles/hello.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/hello.dir/cmake_clean.cmake
.PHONY : CMakeFiles/hello.dir/clean

CMakeFiles/hello.dir/depend:
	cd /home/lb_lb/Projects/dojo/2016/cmake/hello-world && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/lb_lb/Projects/dojo/2016/cmake/hello-world /home/lb_lb/Projects/dojo/2016/cmake/hello-world /home/lb_lb/Projects/dojo/2016/cmake/hello-world /home/lb_lb/Projects/dojo/2016/cmake/hello-world /home/lb_lb/Projects/dojo/2016/cmake/hello-world/CMakeFiles/hello.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/hello.dir/depend

