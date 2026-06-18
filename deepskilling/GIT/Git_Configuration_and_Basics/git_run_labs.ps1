git config --global user.name "Resident"
git config --global user.email "resident@community.org"
git init GitDemo
cd GitDemo
echo "Welcome to City Portal" > welcome.txt
git add welcome.txt
git commit -m "Initial welcome text"
