{-
examples of recursion and patter matching
-}

sumRecursive :: Int -> Int
sumRecursive 0 = 0 -- base case
sumRecursive n = n + sumRecursive (n - 1)

sumTailRecursive :: Int->Int
sumTailRecursive n = sumHelper n 0 -- tail recursive step

sumHelper :: Int -> Int -> Int
sumHelper 0 acc = acc -- base case
sumHelper n acc = sumHelper (n - 1) (n + acc)

--pattern matching to describe a list
describeList :: [a] -> String
describeList [] = "This is an empty list"
describeList [_] = "This is a single element list"
describeList [_,_] = "This is a 2 element list"
describeList (_:_:xs)= "This is a longer list"

indexList :: [a] ->[(Int, a)]
indexList xs = zip [1..] xs

mergeSentences :: [String] -> [String] -> [String]
mergeSentences subject actions = [s ++ "" ++ a | (s, a)<-zip subject actions]


main:: IO ()
main = do
    --putStrLn " start non tail sum "
    --print(sumRecursive 10000000)
    --putStrLn "end non tail sum"
    --putStrLn "start tail sum"
    --print(sumTailRecursive 10000000)
    --putStrLn "end tail sum"
    print $ describeList []
    print $ describeList [42]
    print $ describeList [41, 4]
    print $ describeList [3, 4, 5]
    print $ describeList [5,6,7,8,5,4,3]

    print $ indexList ["apple", "bannanas", "oranges", "cherries"]
    print $ mergeSentences ["Robert", "Students"] ["teaches", "learns"]