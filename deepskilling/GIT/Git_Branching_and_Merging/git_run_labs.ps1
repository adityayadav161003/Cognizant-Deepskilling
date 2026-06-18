git branch GitNewBranch
git checkout GitNewBranch
echo "New features description" > feature.txt
git add feature.txt
git commit -m "Commit feature to GitNewBranch"
git checkout master
git merge GitNewBranch
git branch -d GitNewBranch
