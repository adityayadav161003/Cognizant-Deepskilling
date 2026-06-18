git checkout -b GitWork
echo "<xml>Hello</xml>" > hello.xml
git add hello.xml
git commit -m "Work branch version"
git checkout master
echo "<xml>Hello World</xml>" > hello.xml
git add hello.xml
git commit -m "Master branch version conflict"
git merge GitWork
# Resolve conflict manually by keeping both
echo "<xml>Merged Hello World</xml>" > hello.xml
git add hello.xml
git commit -m "Resolve merge conflict"
