#!/usr/bin/env ruby
# encoding: UTF-8
require 'nokogiri'
filenum = ARGV[0] || 7
puts "reading and parsing xml#{filenum}.xml..."
f = File.open "XMLFILES/xml#{filenum}.xml"
doc = Nokogiri::XML f do |config|
	config.options = Nokogiri::XML::ParseOptions::STRICT | Nokogiri::XML::ParseOptions::NONET
end
f.close
puts "done\nsearching document for arrivals..."
aankomst_vervoer = doc.css 'aankomst soort_vervoer'
a = Array.new
aankomst_vervoer.each do |v|
	a << v.inner_html
end
h1 = Hash.new 0
a.each do |v|
	h1[v] += 1
end
puts "done\nsearching documents for departures..."
vertrek_vervoer = doc.css 'vertrek soort_vervoer'
a = Array.new
vertrek_vervoer.each do |v|
	a << v.inner_html
end
h2 = Hash.new 0
a.each do |v|
	h2[v] += 1
end
puts "done\naankomst:"
h1.each do |k,v|
	puts "    #{k}: #{v} containers"
end
puts "vertrek:"
h2.each do |k,v|
	puts "    #{k}: #{v} containers"
end
