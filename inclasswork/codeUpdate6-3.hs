{-
time and fold` example
-}
import Data.List (foldl')
import Data.Time (getCurrentTime)

--lazy product def haskell behavior
factorialLazy :: Integer -> Integer
factorialLazy n = product [1..n]

--strict product using foldl'
factorialStrict :: Integer -> Integer
factorialStrict n = foldl' (*) 1[1..n]

main :: IO()
main = do
    putStrLn "enter a number to compute factorial"
    input <- getLine
    let num = read input :: Integer
    currentTime <- getCurrentTime
    putStrLn $ "Current system time: " ++ show currentTime
    putStrLn $ "Lazy factorial of " ++ show num ++ " is " ++ show (factorialLazy num)
    currentTime <- getCurrentTime
    putStrLn $ "Current system time: " ++ show currentTime
    putStrLn $ "Strict factorial of " ++ show num ++ " is: " ++ show (factorialStrict num)
    currentTime <- getCurrentTime
    putStrLn $ "Current system time: " ++ show currentTime
