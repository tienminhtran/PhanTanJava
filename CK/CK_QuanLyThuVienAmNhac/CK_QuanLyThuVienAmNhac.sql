-- Chèn dữ liệu vào bảng Genre
INSERT INTO genres (genre_id, name, description) VALUES 
('1', 'Rock', 'Rock music genre')
INSERT INTO genres (genre_id, name, description) VALUES 
('2', 'Pop', 'Pop music genre')
INSERT INTO genres (genre_id, name, description) VALUES 
('3', 'Hip Hop', 'Hip Hop music genre')
INSERT INTO genres (genre_id, name, description) VALUES 
('4', 'Electronic', 'Electronic music genre')
INSERT INTO genres (genre_id, name, description) VALUES 
('5', 'Jazz', 'Jazz music genre')
INSERT INTO genres (genre_id, name, description) VALUES 
('6', 'Rop', 'Rop music')
INSERT INTO genres (genre_id, name, description) VALUES 
('7', 'Rop', 'Rop music')



-- Chèn dữ liệu vào bảng Album
INSERT INTO albums (album_id, title, price, year_of_release, download_link, genre_id) VALUES 
('1', 'Album 1', 10.99, 2022, 'https://example.com/album1', '1')
INSERT INTO albums (album_id, title, price, year_of_release, download_link, genre_id) VALUES 
('2', 'Album 2', 12.99, 2023, 'https://example.com/album2', '2')
INSERT INTO albums (album_id, title, price, year_of_release, download_link, genre_id) VALUES 
('3', 'Album 3', 9.99, 2021, 'https://example.com/album3', '3')
INSERT INTO albums (album_id, title, price, year_of_release, download_link, genre_id) VALUES 
('4', 'Album 4', 11.99, 2024, 'https://example.com/album4', '4')
INSERT INTO albums (album_id, title, price, year_of_release, download_link, genre_id) VALUES 
('5', 'Album 5', 8.99, 2020, 'https://example.com/album5', '5')
INSERT INTO albums (album_id, title, price, year_of_release, download_link, genre_id) VALUES 
('6', 'Album 6', 12.99, 2021, 'https://example.com/album5', '5')
INSERT INTO albums (album_id, title, price, year_of_release, download_link, genre_id) VALUES 
('7', 'Album 7', 12.99, 2021, 'https://example.com/album5', '7')

-- Chèn dữ liệu vào bảng Song
-- Chèn dữ liệu vào bảng Artist
INSERT INTO artists (artist_id, name, birth_date, url) VALUES 
('1', 'John Doe', '1990-05-15', 'https://example.com')
INSERT INTO artists (artist_id, name, birth_date, url) VALUES 
('2', 'Jane Smith', '1985-08-20', 'https://example.com')
INSERT INTO artists (artist_id, name, birth_date, url) VALUES 
('3', 'Michael Johnson', '1978-03-10', 'https://example.com')
INSERT INTO artists (artist_id, name, birth_date, url) VALUES 
('4', 'Emily Brown', '1995-11-25', 'https://example.com')
INSERT INTO artists (artist_id, name, birth_date, url) VALUES 
('5', 'Daniel Wilson', '1980-06-05', 'https://example.com')
INSERT INTO artists (artist_id, name, birth_date, url) VALUES 
('6', 'Jonl', '1971-06-05', 'https://example.com')
INSERT INTO artists (artist_id, name, birth_date, url) VALUES 
('7', 'Jonl', '1971-06-05', 'https://example.com')





INSERT INTO songs (song_id, name, runTime, lyric, file_link) VALUES 
('1', 'Song 1', '3:45', 'Song 1 lyrics...', 'https://example.com/song1')
INSERT INTO songs (song_id, name, runTime, lyric, file_link) VALUES 
('2', 'Song 2', '4:20', 'Song 2 lyrics...', 'https://example.com/song2')
INSERT INTO songs (song_id, name, runTime, lyric, file_link) VALUES 
('3', 'Song 3', '5:10', 'Song 3 lyrics...', 'https://example.com/song3')
INSERT INTO songs (song_id, name, runTime, lyric, file_link) VALUES 
('4', 'Song 4', '3:30', 'Song 4 lyrics...', 'https://example.com/song4')
INSERT INTO songs (song_id, name, runTime, lyric, file_link) VALUES 
('5', 'Song 5', '4:15', 'Song 5 lyrics...', 'https://example.com/song5')
INSERT INTO songs (song_id, name, runTime, lyric, file_link) VALUES 
('6', 'Song 6', '3:15', 'Song 6 lyrics...', 'https://example.com/song5')
INSERT INTO songs (song_id, name, runTime, lyric, file_link) VALUES 
('7', 'Song 7', '3:15', 'Song 7 lyrics...', 'https://example.com/song5')


-- Chèn dữ liệu vào bảng Album_Artist
INSERT INTO albums_artists (album_id, artist_id) VALUES 
('1', '1')
INSERT INTO albums_artists (album_id, artist_id) VALUES 
('2', '2')
INSERT INTO albums_artists (album_id, artist_id) VALUES 
('3', '3')
INSERT INTO albums_artists (album_id, artist_id) VALUES 
('4', '4')
INSERT INTO albums_artists (album_id, artist_id) VALUES 
('5', '5')
INSERT INTO albums_artists (album_id, artist_id) VALUES 
('6', '6')
INSERT INTO albums_artists (album_id, artist_id) VALUES 
('7', '7')
-- Chèn dữ liệu vào bảng Album_Song
INSERT INTO albums_songs (album_id, song_id) VALUES 
('1', '1')
INSERT INTO albums_songs (album_id, song_id) VALUES 
('2', '2')
INSERT INTO albums_songs (album_id, song_id) VALUES 
('3', '3')
INSERT INTO albums_songs (album_id, song_id) VALUES 
('4', '4')
INSERT INTO albums_songs (album_id, song_id) VALUES 
('5', '5')
INSERT INTO albums_songs (album_id, song_id) VALUES 
('6', '6')
INSERT INTO albums_songs (album_id, song_id) VALUES 
('7', '7')

SELECT * FROM [dbo].[genres]

SELECT * FROM [dbo].[albums]
SELECT * FROM [dbo].[albums_artists]
SELECT * FROM [dbo].[albums_songs]
SELECT * FROM [dbo].[artists]
SELECT * FROM [dbo].[songs]

Select g.* from genres g join albums a 
on g.genre_id = a.[genre_id]
where g.name like '%o%' and a.year_of_release = 2021

