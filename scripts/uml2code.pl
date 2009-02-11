#!/usr/bin/perl
# 
# Script to convert a loose relationship class diagram into CRC
# 
# This probably doesn't work, but it's a good start for scripts
# that read UMLet files.
#

use warnings;
use strict;

local $/;
$_ = <>;

local @_;

for my $match (/<element[^>]*>(.*?)<\/element>/sig) {
    my @tags = $match =~ /<([a-z_]+)>/sig;
    local %_ = map {$match =~ /<$_>(.*?)<\/$_>/si; $_ => $1} @tags;
   
    next unless $_{type} =~ /class/i;
   
    next unless $_{panel_attributes} =~ /--/;
    
    $_{panel_attributes} =~ /^(.*?)\n--\n(.*?)\n?--\n?(.*?)/s or die $_{panel_attributes};
    my $name = $1;
    my $attributes = $2;
    my $methods = $3;
    
    push @_, {
        name => $name,
        attributes => $attributes,
        methods => $methods,
    };
}

for(@_) {
    my $interface = $_->{name} =~ s/\s*&lt;&lt;interface&gt;&gt;\s*//;
    my $abstract = $_->{name} =~ s/\s*\{abstract\}\s*//;
    $_->{name} =~ s/\s+//;
    open my $fh, ">".$_->{name}.".java" or die;
    printf($fh "%s %s %s {\n%s\n}\n\n",
        $abstract ? 'public abstract' : 'public',
        $interface ? 'interface' : 'class',
        $_->{name},
        $_->{attributes},
        $_->{methods},
    );
    close $fh;
}
