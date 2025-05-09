import Data.Maybe(fromMaybe)
{-
Monad examples
-}

--sequencing IO action
greetUser :: IO ()
greetUser =
    putStr "Enter your name: " >>
    getLine >>= \name ->
    putStrLn ("Hello, " ++ name ++ "!")

--sequencing computations with maybe monad
safeDivide :: Double -> Double -> Maybe Double
safeDivide _ 0 = Nothing
safeDivide x y = Just (x / y)

computeDivision :: Double -> Double -> Double -> Maybe Double
computeDivision x y z = do
    result1 <- safeDivide x y
    safeDivide result1 z

-- encapsulating error handling with either
safeDivide' :: Double -> Double -> Either String Double
safeDivide' _ 0 = Left "Cant divide by 0"
safeDivide' x y = Right (x / y)

compute :: Double -> Double -> Double -> Either String Double
compute x y z = do
    result1 <- safeDivide' x y
    safeDivide' result1 z

safeSqrt :: Double -> Maybe Double
safeSqrt x | x < 0 = Nothing
           | otherwise = Just (sqrt x)

complexOperation :: Double -> Double -> Double -> Maybe Double
complexOperation a b c =
    safeDivide a b >>= \divResult ->
    safeSqrt divResult >>= \sqrtResult ->
    safeDivide sqrtResult c >>= \finalResult ->
    return finalResult

printResult :: Maybe Double -> IO ()
printResult result = putStrLn $ "Result: " ++ show (fromMaybe 0 result)

main :: IO ()
main = do
    greetUser

    putStrLn "Compute"
    print $ computeDivision 70 80 2
    print (computeDivision 50 0 2)

    putStrLn "Complex Operation"
    print $ complexOperation 65 15 3
    print $ complexOperation 45 (-20) 10
    print $ complexOperation 70 10 0

    putStrLn "Complex Operation without just"
    printResult $ complexOperation 90 10 3
    print $ complexOperation 30 (-60) 10
    print $ complexOperation 70 20 0