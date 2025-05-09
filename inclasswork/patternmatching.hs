{-
Micah Alummoottil
2/7/25

fill in the functions below, there is no need to change main.
-}

import System.IO

-- Define the type for family data  (done for you)
type Family = (String, String, Int)

-- Read the file and parse it into a list of Family tuples  (done for you)
parseFamilyData :: String -> [Family]
parseFamilyData content =
    map parseLine (lines content)
    where
        parseLine line = let parts = split ',' line
                         in (trim (parts !! 0), trim (parts !! 1), read (trim (parts !! 2)) :: Int)

-- Utility function to split a string by a delimiter (Done for you -used to read in file)
split :: Char -> String -> [String]
split _ [] = []
split delim str =
    let (first, rest) = break (== delim) str
    in first : case rest of
        [] -> []
        (_:xs) -> split delim xs

-- Trim whitespace from a string (Done for you -used to read in file)
trim :: String -> String
trim = unwords . words

-- 1. Count the number of families
countFamilies :: [Family] -> Int
countFamilies x = length x

-- 2. Compute the total number of family members
totalMembers :: [Family] -> Int
totalMembers [] = 0
totalMembers ((_, _, n):rest) = n + totalMembers rest

-- 3. Find the family with the most members
largestFamily :: [Family] -> (String, Int)
largestFamily families = biggestVal
  where
    formattedFamilies = [(name, n) | (name, _, n) <- families]
    biggestVal = foldr1(\(maxName, maxN) (name, n) -> if n > maxN then (name, n) else (maxName, maxN))formattedFamilies

-- 4. Filter families with more than a given number of members
filterLargeFamilies :: Int -> [Family] -> [Family]
filterLargeFamilies n families = filter (\(_, _, size) -> size > n) families

-- 5. Extract and format addresses
formatAddresses :: [Family] -> [String]
formatAddresses families = [address | (_, address, _) <- families]

-- Main function to read the file and execute the functions
main :: IO ()
main = do
    handle <- openFile "families.txt" ReadMode
    content <- hGetContents handle
    let familyData = parseFamilyData content

    putStrLn $ "Number of families: " ++ show (countFamilies familyData)
    putStrLn $ "Total number of people: " ++ show (totalMembers familyData)
    putStrLn $ "Largest family: " ++ show (largestFamily familyData)
    putStrLn $ "Families with more than 3 members: " ++ show (filterLargeFamilies 3 familyData)
    putStrLn $ "Addresses: " ++ show (formatAddresses familyData)

    hClose handle
