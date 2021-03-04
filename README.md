# What is Cache and Why do we use Cache?
Cache is nothing but storage for fast access and updates. Let's say you run a website. As the website owner, you want to be able to serve up pages
as fast as possible. When a user requests a page, you open the corresponding file on disk, read in the HTML and send it back over the network. This is 
fine, however when considering speed, since accessing disk can take a while, it can be a bit slow.	

Naturally, some pages will have more requests than otheres by the users and so ideally, you would want to read it in only once from the disk, then 
keep the page in memory so you can quickly send it out again when requested. This is what it means to cache

However, something to keep in mind is that the cache is not like disks. They are small and so you can't fit everything into a cache, so you're still 
going to have to use the larger, but slower disk from time to time.

So the question is: How do you decide what the cache should store? One idea is called the LRU Cache

## What is an LRU Cache?
LRU stands for Least Recently Used. The idea of an LRU Cache is for n elements that the cache can store, the cache stores n elements accessed 
most frequently.

To implement an LRU Cache, we need to use two data structures: A doubly linked list and a Hash Map. In the linked list, the head of the node
represents the most recently used element, and the tail of the node represents the least recently used.

## Using Redis to build a LRU Cache

