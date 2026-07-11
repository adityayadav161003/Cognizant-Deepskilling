# Git Version Control Cheat Sheet

Quick reference for Git configuration, branches, merge operations, and remote collaboration.

## 1. Setup & Configuration
```bash
# Check installed git version
git --version

# Set global user identity
git config --global user.name "John Doe"
git config --global user.email "johndoe@example.com"

# List configurations
git config --list
```

## 2. Basic Operations
```bash
# Initialize a new local repository
git init

# Stage modifications to index
git add file.txt
git add .

# Record staged modifications to history
git commit -m "Commit message"

# Display status of files (untracked, modified, staged)
git status

# Inspect commit logs
git log --oneline --graph --all
```

## 3. Branching and Merging
```bash
# List all local branches (* indicates active branch)
git branch

# Create a new branch
git branch feature-name

# Switch active branch
git switch feature-name
# or (legacy):
git checkout feature-name

# Merge changes from feature branch into current branch
git merge feature-name

# Delete local branch (post merge)
git branch -d feature-name
```

## 4. Conflict Resolution
When modifications to the same line occur on different branches, Git flags a merge conflict during merge:

1. **Locate Conflicts**: Git marks conflict blocks in files using:
   ```txt
   <<<<<<< HEAD
   Current branch modifications
   =======
   Target branch modifications
   >>>>>>> feature-branch
   ```
2. **Resolve**: Open files, edit lines to clean up conflicts, and delete the syntax markers.
3. **Commit**: Stage resolved files and finalize:
   ```bash
   git add resolved_file.txt
   git commit -m "Resolve merge conflict between main and feature-branch"
   ```

## 5. Remote Operations
```bash
# Clone a remote repository locally
git clone https://github.com/user/repo.git

# Show configured remote locations
git remote -v

# Push commits to remote repository
git push origin branch-name

# Pull updates from remote repository (fetch + merge)
git pull origin branch-name
```
