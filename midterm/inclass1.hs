{-Micah Alummoottil
    3/4/25
-}

{- Write the functions sumList
                       doubleSum
                       sumEvenNumbers
                       SumDoubleEvenNumbers
Then, use ghci to test each function
When ready, compile this program and upload
your code and paste your output to the questions below. -}

sumList :: [Int] -> Int
sumList l = sum l

doubleSum :: [Int] -> Int
doubleSum l = 2 * sumList l 

sumEvenNumbers :: [Int] -> Int
sumEvenNumbers l = sumList (filter even l)

sumDoubleEvenNumbers :: [Int] -> Int
sumDoubleEvenNumbers l = 2 * sumEvenNumbers l

main :: IO ()
main = do
  let numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
  print (sumList numbers)               -- Output: 55
  print (doubleSum numbers)             -- Output: 110
  print (sumEvenNumbers numbers)        -- Output: 30
  print (sumDoubleEvenNumbers numbers)  -- Output: 60