{-Micah Alummoottil
  2/28/25
  (by entering your name and date you certify this is your
  own work and not that of any other person or service.
  Comment your functions for clarity.-}

import System.IO
import Data.Maybe
import Control.Monad
import Data.List (partition)
import Text.Read (readMaybe)

-- Book structure: (Title, Author, Availability)
type Book = (String, String, Bool)

-- Function to read books from the file
readBooks :: FilePath -> IO(Maybe [Book])
readBooks "" = return Nothing
readBooks fileName = do
    contents <- readFile fileName
    return $ Just (map parseBook (lines contents))
  where
    parseBook line = let [title, author, available] = wordsWhen (== ';') line
                     in (title, author, read available :: Bool)

-- Function to display books that are available
listAvailableBooks :: [Book] -> IO ()
listAvailableBooks books = do
    putStrLn "Available Books:"
    forM_ books $ \(title, author, available) -> 
        when available $ putStrLn $ title ++ " by " ++ author

-- Function to check out a book (set availability to False)
checkOutBook :: String -> [Book] -> IO (Maybe [Book])
checkOutBook title books = do
    let (found, rest) = partition (\(t, _, _) -> t == title) books
    if null found
        then putStrLn "Book not found!" >> return Nothing
        else do
            let (book:_) = found
            putStrLn $ "Checked out: " ++ title
            return $ Just (map (\book' -> if book' == book then (fst3 book', snd3 book', False) else book') books)

-- Function to return a book (set availability to True)
returnBook :: String -> [Book] -> IO (Maybe [Book])
returnBook title books = do
    let (found, rest) = partition (\(t, _, _) -> t == title) books
    if null found
        then putStrLn "Book not found!" >> return Nothing
        else do
            let (book:_) = found
            putStrLn $ "Returned: " ++ title
            return $ Just (map (\book' -> if book' == book then (fst3 book', snd3 book', True) else book') books)

-- Utility function to extract the first element of a tuple
fst3 :: (a, b, c) -> a
fst3 (x, _, _) = x

-- Utility function to extract the second element of a tuple
snd3 :: (a, b, c) -> b
snd3 (_, x, _) = x

-- Utility function to split a string by a delimiter
wordsWhen :: (Char -> Bool) -> String -> [String]
wordsWhen p s = case dropWhile p s of
    "" -> []
    s' -> w : wordsWhen p s''
          where (w, s'') = break p s'

decision :: Maybe Int -> [Book] -> IO [Book]
decision Nothing books = putStrLn "Invalid choice, please try again." >> return books
decision (Just choice) books =  
    case choice of
        1 -> listAvailableBooks books >> return books
        2 -> do
            putStr "Enter the title: "
            title <- getLine
            checkOutBook title books >>= \check ->
                case check of
                    Nothing -> return books
                    Just bookList -> return bookList
        3 -> do
            putStr "Enter the title: "
            title <- getLine
            returnBook title books >>= \check ->
                case check of
                    Nothing -> return books
                    Just bookList -> return bookList
        4 -> putStrLn "Exiting" >> return books
        _ -> putStrLn "Invalid choice, please try again." >> return books

menu :: [Book] -> IO (Maybe Int)
menu books = do
    putStrLn "Welcome to the Library System!"
    putStrLn "1. List Available Books"
    putStrLn "2. Check Out a Book"
    putStrLn "3. Return a Book"
    putStrLn "4. Exit"
    putStr "Enter your choice: "
    input <- getLine
    return (readMaybe input :: Maybe Int)
    

main :: IO ()
main = do
    books <- readBooks "library.txt" 
    case books of
        Nothing -> putStrLn "Error"
        Just books -> do
            maybeNum <- menu books
            bookList <- decision maybeNum books
            unless (maybeNum == Just 4) main
{-
ToDo:
Complete any functions,
Write any additional functions,
Complete the Main
-}