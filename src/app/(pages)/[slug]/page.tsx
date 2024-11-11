'use client'

import React, { useEffect, useState } from 'react'
import { Metadata } from 'next'
import { draftMode } from 'next/headers'
import { notFound } from 'next/navigation'
import { Blocks } from '../../_components/Blocks'
import Categories from '../../_components/Categories'
import type { Category, Page } from '../../../payload/payload-types'
import { fetchDoc } from '../../_api/fetchDoc'
import { fetchDocs } from '../../_api/fetchDocs'
import { generateMeta } from '../../_utilities/generateMeta'
import { Gutter } from '../../_components/Gutter'
import { Hero } from '../../_components/Hero'
import Promotion from '../../_components/Promotion'
import { staticHome } from '../../../payload/seed/home-static'

import classes from './index.module.scss'

export const dynamic = 'force-dynamic'

export default function Page({ params: { slug = 'home' } }) {
  const [page, setPage] = useState<Page | null>(null)
  const [categories, setCategories] = useState<Category[] | null>(null)

  useEffect(() => {
    const fetchData = async () => {
      try {
        const { isEnabled: isDraftMode } = draftMode()
        const fetchedPage = await fetchDoc<Page>({
          collection: 'pages',
          slug,
          draft: isDraftMode,
        })
        const fetchedCategories = await fetchDocs<Category>('categories')
        setPage(fetchedPage)
        setCategories(fetchedCategories)
      } catch (error) {
        // handle error (optional: log it or show a fallback message)
      }
    }
    fetchData()
  }, [slug])

  if (!page && slug === 'home') {
    return <PageContent page={staticHome} categories={null} />
  }

  if (!page) {
    return notFound()
  }

  return <PageContent page={page} categories={categories} />
}

function PageContent({ page, categories }) {
  const { hero, layout } = page
  const isHomePage = page.slug === 'home'

  return (
    <>
      {isHomePage ? (
        <section>
          <Hero {...hero} />
          <Gutter className={classes.home}>
            <Categories categories={categories} />
            <Promotion />
          </Gutter>
        </section>
      ) : (
        <>
          <Hero {...hero} />
          <Blocks
            blocks={layout}
            disableTopPadding={!hero || hero?.type === 'none' || hero?.type === 'lowImpact'}
          />
        </>
      )}
    </>
  )
}

export async function generateStaticParams() {
  try {
    const pages = await fetchDocs<Page>('pages')
    return pages?.map(({ slug }) => slug)
  } catch (error) {
    return []
  }
}

export async function generateMetadata({ params: { slug = 'home' } }): Promise<Metadata> {
  const { isEnabled: isDraftMode } = draftMode()

  let page: Page | null = null

  try {
    page = await fetchDoc<Page>({
      collection: 'pages',
      slug,
      draft: isDraftMode,
    })
  } catch (error) {
    // handle error (optional: log it or show a fallback message)
  }

  if (!page && slug === 'home') {
    page = staticHome
  }

  return generateMeta({ doc: page })
}
