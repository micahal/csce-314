{-
lazy evaluation complete
-}
lazyDivide :: Bool -> Int
lazyDivide False = 42
lazyDivide True = 1 `div` 0

lazyNumbers :: [Integer]
lazyNumbers = [1..] -- an infinite list of natural nums

fibs :: [Integer]
fibs = 0 : 1 : zipWith (+) fibs (tail fibs)

main :: IO ()
main = do
    print (lazyDivide False)
    --print (lazyDivide True)
    print(take 10 lazyNumbers)

    print(take 100 fibs)
    