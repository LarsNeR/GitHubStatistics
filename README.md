# GitHubStatistics
Get statistics of git folders
  
# Statistics
- Number of repos
- Lines of code (for specific file endings)
- Distribution of languages (for specific file endings)

# How to start
Arguments:
- GitHub Root folder
  - The folder in which all git projects are
  
# Preconditions

This project assumes that you have all git projects in one root directory:
- my_git_folder
  - project1
    - .git
    - src
      - main
        - java
          - Main.java
    - File.java
  - project_abc
    - .git
    - src
      - Main.py
    - target
    - File.py
  - proejct_node
    - .git
    - node_modules
    - src
      -index.html
      
If you pass the path to *my_git_folder* it will scan all files in the folders *project1*, *project_abc* and *project_node*. Furthermore it will scan recursively all files in the folders *project1.src*, *project_abc.src* and *project_node.src*.
