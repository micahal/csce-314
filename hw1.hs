import System.IO
import Text.Printf


{-Micah Alummoottil
  2/3/25
  (by entering your name and date you certify this is your
  own work and not that of any other person or service.
  Comment your functions for clarity.-}

{-Here the image size is defined as a 600 x 600 pixel image-}
-- Define image size
mySize :: Int
mySize = 600

{-Here I have defined 2 rgb colors for a given pixel. -}
-- Define colors  
whiteR, whiteG, whiteB :: Int
whiteR = 255
whiteG = 255
whiteB = 255

maroonR, maroonG, maroonB :: Int
maroonR = 88
maroonG = 0
maroonB = 0
--creating green
greenR, greenG, greenB :: Int
greenR = 20
greenG = 225
greenB = 52
--creating blue
blueR, blueG, blueB :: Int
blueR = 20
blueG = 88
blueB = 225
--creating yellow
yellowR, yellowG, yellowB :: Int
yellowR = 213
yellowG = 224
yellowB = 20

lightR, lightG, lightB :: Int
lightR = 25
lightG = 130
lightB = 180


{-define other colors here, or create them as you move, like gradients-}

generateImage :: [[(Int, Int, Int)]]
generateImage = [[pixelColor i j | j <- [0..mySize-1]] | i <- [0..mySize-1]]
   where
    pixelColor i j
        | i > j && i >= 100 && i < 150 && j >= 100 && j < 150 = (greenR, greenG, greenB)
        | i > 0 && i <= 75 && j > 0 && j <= 75 = (yellowR, yellowG, yellowB)
        | (i - 300)^2 + (j - 300)^2 <= 50^2 = (maroonR, maroonG, maroonB)
        | i > j && i >= 400 && i < 450 && j >= 400 && j < 450 = (lightR, lightG, lightB)
        | i > 200 && i < 250 && j > (i - 150) && j < (300 - (i - 200)) = (blueR, blueG, blueB)
        | i > 300 && i <= 450 && j > 75 && j <= 125 = (yellowR, yellowG, yellowB)
        | i > 350 && i <= 400 && j > 25 && j <= 175 = (yellowR, yellowG, yellowB)
        | (i^2) `div` (8^2) + (j^2) `div` (4^2) <= 1 && 325 < i && i < 400 && 325 < j && j < 400 = (maroonR, maroonG, maroonB)
        |otherwise  = (whiteR, whiteG, whiteB)     -- White 
 --Update this. use at least 5 different colors and 4 different shapes.

-- Write the PPM file
writePPM :: FilePath -> [[(Int, Int, Int)]] -> IO ()
writePPM filename pixels = do
    let header = "P3\n" ++ show mySize ++ " " ++ show mySize ++ "\n255\n"
        body = unlines [unwords [printf "%d %d %d" r g b | (r, g, b) <- row] | row <- pixels]
    writeFile filename (header ++ body)

main :: IO ()
main = do
    let image = generateImage
    writePPM "output.ppm" image
    putStrLn "PPM file 'output.ppm' created successfully."