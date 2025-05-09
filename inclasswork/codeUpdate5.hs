import Data.Maybe (fromMaybe)
-- define shape ADT with constructors
data Shape = Circle Double
    | Rectangle Double Double
    | Triangle Double Double Double
    deriving (Show, Read)
--function to validate triangle
isValidTriangle :: Double -> Double -> Double -> Bool
isValidTriangle a b c = a + b > c && a + c > b && c + b > a

--create function to cal area of shape
area :: Shape -> Maybe Double
area (Circle r) = Just (pi * r * r)
area (Rectangle h w) = Just (w * h)
area (Triangle a b c) 
    | isValidTriangle a b c = Just (let s = (a + b + c) / 2 in sqrt(s * (s - a) * (s - b) * (s - c)))
    | otherwise = Nothing -- for invalid triangle

-- format maybe result into user friendly string
formatResult :: Maybe Double -> String
formatResult = maybe "Invalid shape" show 

--function to handle user input
getShapeFromUser :: IO Shape
getShapeFromUser = do
    putStrLn "Choose a shape: (1) Circle "
    shapeType <- getLine
    case shapeType of
        "1" -> do
            putStrLn "Enter Radius: "
            Circle <$> readLn
        "2" -> do
            putStrLn "Enter width: "
            w <- readLn
            putStrLn "Enter height: "
            Rectangle <$> readLn
        "3" -> do
            putStrLn "Enter Side A"
            a <- readLn
            putStrLn "Enter Side B"
            b <- readLn
            putStrLn "Enter side C"
            Triangle <$> readLn
        _ -> do
            putStrLn "Invalid choice, defaulting to Circle with r = 1"
            return (Circle 1)

--main function to handle user input and display results
main :: IO ()
main = do
    putStrLn "Welcome to the Shape Calculator"
    shape <- getShapeFromUser
    putStrLn "\nShape: " ++ show shape
    putStrLn " Area: " ++ formatResult (area shape)

