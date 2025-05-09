-- Step 1: Generate an Arithmetic Sequence (generateSequence)
-- look at the code in main to determine the signature, 
-- then the code to create the sequence
generateSequence :: Int -> Int -> Int -> [Int]
generateSequence start step n = [start + i * step | i <- [0 .. n - 1]]

-- Step 2: Filter Values Based on a Condition
-- return a list of numbers that are evenly divided by your divisor
filterDivisible :: Int -> [Int] -> [Int]
filterDivisible d sequence = filter (\x -> x `mod` d == 0) sequence



-- Step 3: Transform the Values
-- create a function to square a value, 
-- then create a function to apply
-- the function to the sequence
square :: Int -> Int
square n = n * n

applyFunction :: (Int -> Int) -> Int -> Int
applyFunction f = f

transformSequence :: [Int] -> [Int]
transformSequence newsequence = map (\x -> applyFunction square x) newsequence


-- Step 4: Calculate the Sum (hint, use foldl and a lambda function)
sumSequence :: [Int] -> Int
sumSequence newlist = foldl (+) 0 newlist


-- Main Program
main :: IO ()
main = do
  putStrLn "Enter the start of the sequence:"
  start <- readLn
  putStrLn "Enter the step size:"
  step <- readLn
  putStrLn "Enter the number of elements to generate:"
  n <- readLn
  putStrLn "Enter the divisor for filtering:"
  divisor <- readLn

  -- Generate the sequence
  let sequence = generateSequence start step n
  putStrLn $ "Generated sequence: " ++ show sequence

  -- Filter the sequence
  let filtered = filterDivisible divisor sequence
  putStrLn $ "Filtered sequence (divisible by " ++ show divisor ++ "): " ++ show filtered

  -- Transform the sequence
  let transformed = transformSequence filtered
  putStrLn $ "Transformed sequence (squared values): " ++ show transformed

  -- Calculate the sum
  let sumTransformed = sumSequence transformed
  putStrLn $ "Sum of transformed sequence: " ++ show sumTransformed

  -- Track filtered count
  putStrLn $ "Number of elements filtered: " ++ show (length filtered)