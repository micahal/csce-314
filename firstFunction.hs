factorial :: Integer -> Integer
factorial 0 = 1
factorial n = n * factorial (n - 1)

main :: IO ()
main = do
    putStrLn "The factorial of 5 is:"
    print (factorial 5)
    
    putStrLn "The double of 2143575545 is:"
    print (2 * 2143575545)
    
    putStrLn "The square of 15 is:"
    print (15 ^ 2)